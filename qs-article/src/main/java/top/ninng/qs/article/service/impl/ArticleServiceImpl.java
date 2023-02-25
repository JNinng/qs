package top.ninng.qs.article.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.ninng.qs.article.clients.EsClient;
import top.ninng.qs.article.config.IdConfig;
import top.ninng.qs.article.entity.*;
import top.ninng.qs.article.mapper.ArticleMapper;
import top.ninng.qs.article.service.IArticleService;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.common.utils.EmptyCheck;
import top.ninng.qs.common.utils.IdObfuscator;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 文章服务实现类
 *
 * @Author OhmLaw
 * @Date 2023/1/6 17:25
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    ArticleMapper articleMapper;
    EsClient esClient;
    RedisTemplate<Object, Object> redisTemplate;
    IdObfuscator idObfuscator;

    public ArticleServiceImpl(ArticleMapper articleMapper, EsClient esClient,
                              RedisTemplate<Object, Object> redisTemplate, IdObfuscator idObfuscator) {
        this.articleMapper = articleMapper;
        this.esClient = esClient;
        this.redisTemplate = redisTemplate;
        this.idObfuscator = idObfuscator;
    }

    @Override
    public UnifyResponse<String> addScore(long id, String mode, String ip, int time) {
        switch (mode) {
            case "article":
                String visitor = "qs:score:cooling:article:" + id + ":" + ip;
                Boolean cooling = (Boolean) redisTemplate.opsForValue().get(visitor);
                if (EmptyCheck.notEmpty(cooling)) {
                    return UnifyResponse.fail("无效！", null);
                }
                redisTemplate.opsForValue().set(visitor, true, 60, TimeUnit.SECONDS);
                String date = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
                double log = Math.log(time / 60.0 + 1);
                System.out.println("score: " + log);
                String scorePool = "qs:score:article:day:" + date;
                redisTemplate.opsForZSet().incrementScore(scorePool, id, log);
                // 加分后增加资源访问量
                Article article = articleMapper.selectByPrimaryKey(id);
                article.setPageView(article.getPageView() + 1);
                articleMapper.updateByPrimaryKey(article);
                break;
            case "user":
                // TODO: 用户模式
                break;
            default:
                break;
        }
        return null;
    }

    /**
     * 获取指定 id 的文章
     *
     * @param id 文章 id
     * @return 文章信息
     */
    @Override
    public UnifyResponse<Article> getArticleById(long id) {
        // 询持久层数据
        Article article = articleMapper.selectByPrimaryKey(id);
        // id 混淆处理
        article.setObfuscatorId(idObfuscator.encode(article.getId(), IdConfig.ARTICLE_ID));
        article.setObfuscatorUserId(idObfuscator.encode(article.getUserId(), IdConfig.USER_ID));
        return UnifyResponse.ok(article);
    }

    /**
     * 分页查询 id
     *
     * @param page     页数
     * @param pageSize 页大小
     * @return 分页结果 id 列表
     */
    @Override
    public UnifyResponse<ArticleIdListPageResult> getArticleIdListByPage(int page, int pageSize) {
        // 处理网页分页逻辑和数据库分页查询逻辑
        page = (page <= 0) ? 1 : page;
        ArrayList<ArticleIdAndTitle> articleIdList =
                articleMapper.selectArticleIdListByPage((page - 1) * pageSize, pageSize)
                        //查询结果处理
                        .stream()
                        // id 混淆
                        .map(aLong -> {
                            ArticleIdAndTitle articleIdAndTitle = articleMapper.selectTitleAndDateByPrimaryKey(aLong);
                            articleIdAndTitle.setId(idObfuscator.encode(Math.toIntExact(aLong), IdConfig.ARTICLE_ID));
                            return articleIdAndTitle;
                        })
                        // 转化为列表
                        .collect(Collectors.toCollection(ArrayList::new));
        return UnifyResponse.ok(new ArticleIdListPageResult(articleIdList, page, pageSize));
    }

    @Override
    public UnifyResponse<ArticleIdListPageResult> getArticleIdListByPageAndId(long userId, int page, int pageSize) {
        // 处理网页分页逻辑和数据库分页查询逻辑
        page = (page <= 0) ? 1 : page;
        ArrayList<ArticleIdAndTitle> articleIdList =
                articleMapper.selectArticleIdListByPageAndUserId(userId, (page - 1) * pageSize, pageSize)
                        //查询结果处理
                        .stream()
                        // id 混淆
                        .map(aLong -> {
                            ArticleIdAndTitle articleIdAndTitle = articleMapper.selectTitleAndDateByPrimaryKey(aLong);
                            articleIdAndTitle.setId(idObfuscator.encode(Math.toIntExact(aLong), IdConfig.ARTICLE_ID));
                            articleIdAndTitle.setContent(articleIdAndTitle.getContent().substring(0,
                                    Math.min(articleIdAndTitle.getContent().length(), 400)));
                            return articleIdAndTitle;
                        })
                        // 转化为列表
                        .collect(Collectors.toCollection(ArrayList::new));
        return UnifyResponse.ok(new ArticleIdListPageResult(articleIdList, page, pageSize));
    }

    /**
     * 获取指定 id 的文章简略信息
     *
     * @param id 文章 id
     * @return 文章信息
     */
    @Override
    public UnifyResponse<Article> getArticleInfoById(long id) {
        // 查询持久层数据
        Article article = articleMapper.selectByPrimaryKey(id);
        // id 混淆
        article.setObfuscatorId(idObfuscator.encode(article.getId(), IdConfig.ARTICLE_ID));
        article.setObfuscatorUserId(idObfuscator.encode(article.getUserId(), IdConfig.ARTICLE_ID));
        // 简略处理
        article.setContent("");
        return UnifyResponse.ok(article);
    }

    @Override
    public UnifyResponse<ArrayList<Article>> getArticleListByPage(int page, int pageSize) {
        // 处理网页分页逻辑和数据库分页查询逻辑
        page = (page <= 0) ? 1 : page;
        ArrayList<Article> articleArrayList = articleMapper.selectArticleByPage((page - 1) * pageSize, pageSize)
                //查询结果处理
                .stream()
                // id 混淆
                .peek(article -> {
                    article.setObfuscatorId(idObfuscator.encode(article.getId(), IdConfig.ARTICLE_ID));
                    article.setObfuscatorUserId(idObfuscator.encode(article.getUserId(), IdConfig.USER_ID));
                })
                // 转化为列表
                .collect(Collectors.toCollection(ArrayList::new));
        ;
        return UnifyResponse.ok(articleArrayList);
    }

    @Override
    public UnifyResponse<ArrayList<Article>> getArticleListByPageAndId(int page, int pageSize, long userId) {
        // 处理网页分页逻辑和数据库分页查询逻辑
        page = (page <= 0) ? 1 : page;
        ArrayList<Article> articleArrayList = articleMapper.selectArticleByPageAndId((page - 1) * pageSize, pageSize,
                userId)
                //查询结果处理
                .stream()
                // id 混淆
                .peek(article -> {
                    article.setObfuscatorId(idObfuscator.encode(article.getId(), IdConfig.ARTICLE_ID));
                    article.setObfuscatorUserId(idObfuscator.encode(article.getUserId(), IdConfig.USER_ID));
                })
                // 转化为列表
                .collect(Collectors.toCollection(ArrayList::new));
        ;
        return UnifyResponse.ok(articleArrayList);
    }

    /**
     * 获取指定 id 的预览版文章
     *
     * @param id 文章 id
     * @return 预览版文章
     */
    @Override
    public UnifyResponse<Article> getArticlePreviewById(long id) {
        // 查询持久层数据
        Article article = articleMapper.selectByPrimaryKey(id);
        // id 混淆
        article.setObfuscatorId(idObfuscator.encode(article.getId(), IdConfig.ARTICLE_ID));
        article.setObfuscatorUserId(idObfuscator.encode(article.getUserId(), IdConfig.ARTICLE_ID));
        // 文章信息预览处理
        String content = article.getContent();
        if (content.indexOf("<!-- more -->") > 0) {
            article.setContent(content.split("<!-- more -->")[0]);
        } else {
            article.setContent("");
        }
        return UnifyResponse.ok(article);
    }

    @Override
    public UnifyResponse<ArticleTimelineMonthResult> getArticleTimelineMonthResult(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        ArrayList<TimelineMonthItem> collect = articleMapper.getArticleIdListByMonth(
                String.valueOf(calendar.get(Calendar.YEAR)),
                String.valueOf(calendar.get(Calendar.MONTH) + 1))
                .stream()
                .peek(aItem -> aItem.setObfuscatorId(
                        idObfuscator.encode(Math.toIntExact(aItem.getId()), IdConfig.ARTICLE_ID)))
                .collect(Collectors.toCollection(ArrayList::new));

        return UnifyResponse.ok(new ArticleTimelineMonthResult(date, collect));
    }

    @Override
    public UnifyResponse<ArticleIdListPageResult> getHot() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
        String scorePool = "qs:score:article:day:" + date;
        ArrayList<ArticleIdAndTitle> list = new ArrayList<>();
        Set<Object> range = redisTemplate.opsForZSet().range(scorePool, 0, 10);
        if (EmptyCheck.notEmpty(range) && range.size() > 0) {
            range.forEach(o -> {
                ArticleIdAndTitle articleIdAndTitle =
                        articleMapper.selectTitleAndDateByPrimaryKey(Long.valueOf((Integer) o));
                articleIdAndTitle.setId(idObfuscator.encode((Integer) o, IdConfig.ARTICLE_ID));
                if (EmptyCheck.notEmpty(articleIdAndTitle)) {
                    list.add(articleIdAndTitle);
                }
            });
            return UnifyResponse.ok(new ArticleIdListPageResult(list, 1, 10));
        }
        return UnifyResponse.fail("暂无榜单信息 ！", null);
    }

    @Override
    public UnifyResponse<PageInfo> getPageInfo() {
        return UnifyResponse.ok(new PageInfo(articleMapper.selectArticleTotal()));
    }

    @Override
    public UnifyResponse<PageInfo> getPageInfoById(long userId) {
        return UnifyResponse.ok(new PageInfo(articleMapper.selectArticleTotalById(userId)));
    }

    @Override
    public UnifyResponse<ArticleData> getUserArticleData(long id) {
        ArticleData articleData = new ArticleData();
        ArrayList<Article> articles = articleMapper.selectArticleDataByUserId(id);
        if (articles.size() > 0) {
            articleData.setArticleNumber(articles.size());
            long pageViewNumber = articles.stream().mapToInt(Article::getPageView).sum();
            long getLikes = articles.stream().mapToInt(Article::getLikeNum).sum();
            articleData.setPageViewNumber(Math.toIntExact(pageViewNumber));
            articleData.setGetLikes(Math.toIntExact(getLikes));
            return UnifyResponse.ok(articleData);
        }
        return null;
    }

    @Override
    public UnifyResponse<String> saveIndex(long id) {
        //        Article article = articleMapper.selectByPrimaryKey(id);
        int count = articleMapper.selectCount();
        for (int i = 0; i < count / 5 + 1; i++) {
            ArrayList<Article> articles = articleMapper.selectArticleDocumentByPage(i * 5, 5);
            articles.forEach(article -> {
                if (EmptyCheck.notEmpty(article)) {
                    ArticleDocument articleDocument = new ArticleDocument();
                    articleDocument.setId(idObfuscator.encode(article.getId(), IdConfig.ARTICLE_ID));
                    articleDocument.setUserId(idObfuscator.encode(article.getUserId(), IdConfig.USER_ID));
                    articleDocument.setTitle(article.getTitle());
                    articleDocument.setContent(article.getContent());
                    articleDocument.setCreateTime(article.getCreateTime());
                    articleDocument.setUpdateTime(article.getUpdateTime());
                    esClient.saveArticle(
                            idObfuscator.encode(article.getId(), IdConfig.ARTICLE_ID),
                            idObfuscator.encode(article.getUserId(), IdConfig.USER_ID),
                            article.getTitle(),
                            article.getContent(),
                            article.getCreateTime(),
                            article.getUpdateTime()
                    );
                }
            });
        }
        return UnifyResponse.ok();
    }

    /**
     * 根据指定 id 更新文章
     *
     * @param id      文章 id
     * @param userId  当前用户 id
     * @param content 正文
     * @param title   标题
     * @param ip      更新请求地 ip 地址
     * @return 更新结果
     */
    @Override
    public UnifyResponse<String> updateArticleById(long id, long userId, String content, String title, String ip) {
        long articleId = articleMapper.selectUserIdByIdInt(id);
        if (articleId == userId) {
            // 构建更新的文章实体
            Article article = new Article();
            article.setId((int) id);
            article.setContent(content);
            article.setTitle(title);
            article.setIp(ip);
            // 持久层数据更新
            articleMapper.updateByPrimaryKeySelective(article);
            return UnifyResponse.ok("更新成功！", null);
        }
        return UnifyResponse.fail("所有权错误，更新失败！", null);
    }

    /**
     * 根据指定 id 更新文章
     *
     * @param article 文章
     * @return 更新结果
     */
    @Override
    public UnifyResponse<String> updateArticleById(Article article) {
        // 持久层数据更新
        return UnifyResponse.ok(articleMapper.updateByPrimaryKeySelective(article) > 0 ? "更新成功！" : "更新失败！");
    }

    /**
     * 上传文章
     *
     * @param id      用户 id
     * @param content 正文
     * @param title   标题
     * @return 上传结果
     */
    @Override
    public UnifyResponse<String> upload(long id, String content, String title, String ip) {
        //构建新的文章
        Article article = new Article();
        article.setUserId((int) id);
        article.setCreateTime(new Timestamp(System.currentTimeMillis()));
        article.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        article.setDeleteStatus(false);
        article.setContent(content);
        article.setTitle(title);
        article.setIp(ip);
        article.setSite("本地");
        article.setPageView(0);
        article.setLikeNum(0);
        article.setStatus(0);
        article.setStick(0);
        article.setMode(article.getContent().contains("<!-- more -->") ? 1 : 0);
        // 持久层数据插入
        int result = articleMapper.insert(article);
        if (result > 0) {
            return UnifyResponse.ok("上传成功！", null);
        }
        return UnifyResponse.fail("上传失败！", null);
    }
}
