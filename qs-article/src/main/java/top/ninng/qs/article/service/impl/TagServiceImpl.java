package top.ninng.qs.article.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.ninng.qs.article.config.IdConfig;
import top.ninng.qs.article.entity.ArticleIdAndTitle;
import top.ninng.qs.article.entity.ArticleIdListPageResult;
import top.ninng.qs.article.entity.Tag;
import top.ninng.qs.article.mapper.ArticleMapper;
import top.ninng.qs.article.mapper.ArticleTagMapper;
import top.ninng.qs.article.mapper.TagMapper;
import top.ninng.qs.article.service.ITagService;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.common.utils.EmptyCheck;
import top.ninng.qs.common.utils.IdObfuscator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author OhmLaw
 * @Date 2023/1/13 20:50
 * @Version 1.0
 */
@Service
public class TagServiceImpl implements ITagService {

    TagMapper tagMapper;
    ArticleTagMapper articleTagMapper;
    ArticleMapper articleMapper;
    IdObfuscator idObfuscator;
    RedisTemplate<Object, Object> redisTemplate;

    public TagServiceImpl(TagMapper tagMapper, ArticleTagMapper articleTagMapper, ArticleMapper articleMapper,
                          IdObfuscator idObfuscator, RedisTemplate<Object, Object> redisTemplate) {
        this.tagMapper = tagMapper;
        this.articleTagMapper = articleTagMapper;
        this.articleMapper = articleMapper;
        this.idObfuscator = idObfuscator;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 添加标签
     *
     * @param tagName 标签名
     * @return 添加结果
     */
    @Override
    public UnifyResponse<String> addTag(String tagName) {
        // 构建新标签
        Tag tag = new Tag();
        tag.setName(tagName);
        tag.setCreateTime(new Timestamp(System.currentTimeMillis()));
        tag.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        tag.setDeleteStatus(false);
        tag.setType(0);
        tag.setStatus(0);
        if (tagMapper.insert(tag) > 0) {
            return UnifyResponse.ok("添加成功！");
        }
        return UnifyResponse.ok("添加失败！");
    }

    /**
     * 根据 id 删除标签
     *
     * @param id 标签 id
     * @return 删除结果
     */
    @Override
    public UnifyResponse<String> deleteTagById(Long id) {
        if (tagMapper.deleteByPrimaryKey(id) > 0) {
            return UnifyResponse.ok("删除成功！");
        }
        return UnifyResponse.ok("删除失败！");
    }

    /**
     * 根据标签名删除标签
     *
     * @param tagName 标签名
     * @return 删除结果
     */
    @Override
    public UnifyResponse<String> deleteTagByName(String tagName) {
        if (tagMapper.deleteByName(tagName) > 0) {
            return UnifyResponse.ok("删除成功！");
        }
        return UnifyResponse.ok("删除失败！");
    }

    /**
     * 获取所有标签
     *
     * @return 标签列表
     */
    @Override
    public UnifyResponse<ArrayList<Tag>> getAllTag() {
        ArrayList<Tag> arrayList = (ArrayList<Tag>) redisTemplate.opsForValue().get("allTag");
        if (EmptyCheck.notEmpty(arrayList)) {
            return UnifyResponse.ok(arrayList);
        }
        // 持久层数据查询
        ArrayList<Tag> tagList = tagMapper.selectAll();
        tagList = tagList.stream()
                .peek((tag -> tag.setSum(articleTagMapper.selectCountByTag(Long.valueOf(tag.getId())))))
                .sorted(Comparator.comparing(Tag::getSum).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
        if (EmptyCheck.notEmpty(tagList)) {
            tagList = tagList.stream()
                    // id 混淆
                    .peek(this::obfuscatorId)
                    // 转化列表输出
                    .collect(Collectors.toCollection(ArrayList::new));
            redisTemplate.opsForValue().set("allTag", tagList, 60, TimeUnit.SECONDS);
            return UnifyResponse.ok(tagList);
        }
        return UnifyResponse.ok("暂无标签！", null);
    }

    /**
     * 根据标签名分页查询文章 id 列表
     *
     * @param tagName  标签名
     * @param page     页数
     * @param pageSize 页大小
     * @return 分页查询结果
     */
    @Override
    public UnifyResponse<ArticleIdListPageResult> getArticleIdListPageByName(
            String tagName, int page, int pageSize) {
        // 处理网页分页逻辑和数据库分页查询逻辑
        page = (page <= 0) ? 1 : page;
        // 查询指定标签名的 id
        Long aLong = tagMapper.selectIdByName(tagName);
        if (EmptyCheck.notEmpty(aLong) && aLong > 0) {
            // 分页查询与 id 联系的文章 id 列表
            ArrayList<Long> longArrayList = articleTagMapper.selectArticleIdListByPageByName(aLong,
                    (page - 1) * pageSize, pageSize);
            if (EmptyCheck.notEmpty(longArrayList) && longArrayList.size() > 0) {
                // 结果处理
                ArrayList<ArticleIdAndTitle> collect = longArrayList.stream()
                        // id 混淆
                        .map(aLong1 -> {
                            ArticleIdAndTitle articleIdAndTitle = articleMapper.selectTitleAndDateByPrimaryKey(aLong1);
                            articleIdAndTitle.setId(idObfuscator.encode(Math.toIntExact(aLong1
                            ), IdConfig.ARTICLE_ID));
                            return articleIdAndTitle;
                        })
                        .sorted(Comparator.comparing(ArticleIdAndTitle::getDate).reversed())
                        // 转换输出列表
                        .collect(Collectors.toCollection(ArrayList::new));
                return UnifyResponse.ok(new ArticleIdListPageResult(collect, page, pageSize));
            }
            return UnifyResponse.ok("暂无文章！", null);
        }
        return UnifyResponse.ok("标签错误！", null);
    }

    /**
     * 根据 id 获取标签
     *
     * @param id 标签 id
     * @return 标签
     */
    @Override
    public UnifyResponse<Tag> getTagById(long id) {
        Tag tag = tagMapper.selectByPrimaryKey(id);
        if (EmptyCheck.notEmpty(tag)) {
            return UnifyResponse.ok(tag);
        }
        return UnifyResponse.ok("id 错误！", null);
    }

    /**
     * 根据 id 更新标签
     *
     * @param id 标签 id
     * @return 更新结果
     */
    @Override
    public UnifyResponse<String> updateTagById(Long id, String tagName) {
        Tag tag = new Tag();
        tag.setId(Math.toIntExact(id));
        tag.setName(tagName);
        tag.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        if (tagMapper.updateByPrimaryKey(tag) > 0) {
            return UnifyResponse.ok("更新成功！");
        }
        return UnifyResponse.ok("更新失败！");
    }

    /**
     * 对 id 进行混淆
     *
     * @param tag 标签
     */
    private void obfuscatorId(Tag tag) {
        tag.setObfuscatorId(idObfuscator.encode(tag.getId(), IdConfig.TAG_ID));
    }
}
