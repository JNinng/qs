package top.ninng.qs.article.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import top.ninng.qs.article.config.IdConfig;
import top.ninng.qs.article.entity.*;
import top.ninng.qs.article.service.IArticleAmendmentService;
import top.ninng.qs.article.service.IArticleService;
import top.ninng.qs.article.service.IFavoriteService;
import top.ninng.qs.article.utils.Ip;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.common.utils.EmptyCheck;
import top.ninng.qs.common.utils.IdObfuscator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

/**
 * 文章控制器
 *
 * @Author OhmLaw
 * @Date 2023/1/6 17:27
 * @Version 1.0
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    IArticleService iArticleService;
    IFavoriteService iFavoriteService;
    IArticleAmendmentService iArticleAmendmentService;
    IdObfuscator idObfuscator;

    public ArticleController(IArticleService iArticleService, IFavoriteService iFavoriteService,
                             IArticleAmendmentService iArticleAmendmentService,
                             IdObfuscator idObfuscator) {
        this.iArticleService = iArticleService;
        this.iFavoriteService = iFavoriteService;
        this.iArticleAmendmentService = iArticleAmendmentService;
        this.idObfuscator = idObfuscator;
        System.out.println(idObfuscator.encode(1, IdConfig.ARTICLE_ID));
    }

    @RequestMapping(value = "/addScore", method = RequestMethod.POST)
    public UnifyResponse<String> addScore(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "mode") String mode,
            @RequestParam(value = "time") int time,
            HttpServletRequest request) {
        String visitor = request.getHeader("user_ip");
        long[] realId = new long[]{};
        switch (mode) {
            case "article":
                realId = idObfuscator.decode(id, IdConfig.ARTICLE_ID);
                break;
            case "user":
                realId = idObfuscator.decode(id, IdConfig.USER_ID);
                break;
            default:
                break;
        }
        if (realId.length > 0) {
            return iArticleService.addScore(realId[0], mode, visitor, time);
        }
        return UnifyResponse.fail("id 错误！", null);
    }

    @RequestMapping(value = "/deleteFavorite", method = RequestMethod.POST)
    public UnifyResponse<String> deleteFavorite(
            @RequestParam(value = "articleId") String articleIdStr,
            HttpServletRequest request) {
        String userIdStr = request.getHeader("user_id");
        long[] articleId = idObfuscator.decode(articleIdStr, IdConfig.ARTICLE_ID);
        long userId = 0L;
        if (EmptyCheck.notEmpty(userIdStr) && articleId.length > 0) {
            userId = Long.parseLong(userIdStr);
        } else {
            return UnifyResponse.fail("id 错误！", null);
        }
        return iFavoriteService.deleteFavorite(articleId[0], userId);
    }

    @RequestMapping(value = "/getArticleAmendmentInfo", method = RequestMethod.POST)
    public UnifyResponse<ArticleAmendmentInfo> getArticleAmendmentInfo(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize,
            HttpServletRequest request) {
        long[] loginId = new long[]{0L};
        String userId = request.getHeader("user_id");
        if (EmptyCheck.notEmpty(userId)) {
            loginId[0] = Long.parseLong(userId);
            if (loginId[0] == 1) {
                return iArticleAmendmentService.getArticleAmendmentInfo(page, pageSize);
            }
        }
        return UnifyResponse.fail("id 错误！", null);
    }

    /**
     * 根据混淆 id 获取文章
     *
     * @param id 混淆 id
     * @return 指定文章
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UnifyResponse<Article> getArticleById(
            @PathVariable(value = "id") String id) {
        // 解码获得真实 id
        long[] realId = idObfuscator.decode(id, 1);
        // realId 长度为零时混淆 id 错误
        if (realId.length > 0) {
            return iArticleService.getArticleById(realId[0]);
        }
        return UnifyResponse.fail();
    }

    /**
     * 根据页数获取文章混淆 id
     *
     * @param page     页数
     * @param pageSize 页大小
     * @return 指定页内的文章 id 列表
     */
    @RequestMapping(value = "/getIdListPage", method = RequestMethod.POST)
    public UnifyResponse<ArticleIdListPageResult> getArticleIdListByPage(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize) {
        if (EmptyCheck.notEmpty(id)) {
            long[] realId = idObfuscator.decode(id, IdConfig.USER_ID);
            if (realId.length > 0) {
                return iArticleService.getArticleIdListByPageAndId(realId[0], page, pageSize);
            }
        }
        return iArticleService.getArticleIdListByPage(page, pageSize);
    }

    /**
     * 根据混淆 id 获取文章简略信息（无正文）
     *
     * @param id 混淆 id
     * @return 指定文章的简略信息
     */
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public UnifyResponse<Article> getArticleInfoById(
            @PathVariable(value = "id") String id) {
        long[] realId = idObfuscator.decode(id, 1);
        if (realId.length > 0) {
            return iArticleService.getArticleInfoById(realId[0]);
        }
        return UnifyResponse.fail();
    }

    /**
     * 文章无正文信息分页查询
     * role:admin
     *
     * @return 文章信息列表
     */
    @RequestMapping(value = "/getPageList", method = RequestMethod.POST)
    public UnifyResponse<ArrayList<Article>> getArticleListByPage(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize,
            @RequestParam(value = "userId", required = false) String userId,
            HttpServletRequest request) {
        long[] loginId = new long[]{0L};
        if (EmptyCheck.isEmpty(userId)) {
            userId = request.getHeader("user_id");
            if (EmptyCheck.notEmpty(userId)) {
                loginId[0] = Long.parseLong(userId);
                if (loginId[0] == 1) {
                    return iArticleService.getArticleListByPage(page, pageSize);
                }
            } else {
                return UnifyResponse.fail("id 错误！", null);
            }
        } else {
            loginId = idObfuscator.decode(userId, IdConfig.USER_ID);
        }
        return iArticleService.getArticleListByPageAndId(page, pageSize, loginId[0]);
    }

    /**
     * 根据混淆 id 获取文章预览版
     *
     * @param id 混淆 id
     * @return 指定预览版文章
     */
    @RequestMapping(value = "/preview/{id}", method = RequestMethod.GET)
    public UnifyResponse<Article> getArticlePreviewById(
            @PathVariable(value = "id") String id) {
        long[] realId = idObfuscator.decode(id, 1);
        if (realId.length > 0) {
            return iArticleService.getArticlePreviewById(realId[0]);
        }
        return UnifyResponse.fail();
    }

    @RequestMapping(value = "/timeline", method = RequestMethod.POST)
    public UnifyResponse<ArticleTimelineMonthResult> getArticleTimelineMonthResult(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        System.out.println(date);
        return iArticleService.getArticleTimelineMonthResult(date);
    }

    @RequestMapping(value = "/getFavorite", method = RequestMethod.POST)
    public UnifyResponse<FavoriteInfo> getFavorite(
            @RequestParam(value = "userId") String userIdStr,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize) {
        long[] userId = idObfuscator.decode(userIdStr, IdConfig.USER_ID);
        if (userId.length > 0) {
            return iFavoriteService.getFavorite(userId[0], page, pageSize);
        }
        return UnifyResponse.fail("id 错误！", null);
    }

    @RequestMapping(value = "/hot", method = RequestMethod.POST)
    public UnifyResponse<ArticleIdListPageResult> getHot() {
        return iArticleService.getHot();
    }

    /**
     * 获取文章管理的分页信息
     * role:admin
     *
     * @return 文章管理部分分页信息
     */
    @RequestMapping(value = "/pageInfo", method = RequestMethod.GET)
    public UnifyResponse<PageInfo> getPageInfo(
            @RequestParam(value = "userId", required = false) String userId,
            HttpServletRequest request) {
        long[] longId = new long[]{0L};
        if (EmptyCheck.notEmpty(userId)) {
            longId = idObfuscator.decode(userId, IdConfig.USER_ID);
            if (longId.length > 0) {
                if (longId[0] == 1) {
                    return iArticleService.getPageInfo();
                } else {
                    return iArticleService.getPageInfoById(longId[0]);
                }
            }
        }
        String userIdStr = request.getHeader("user_id");
        if (EmptyCheck.notEmpty(userIdStr)) {
            longId[0] = Long.parseLong(userIdStr);
        }
        return iArticleService.getPageInfoById(longId[0]);
    }

    /**
     * 获取用户文章数据：文章总数、被收藏数、总访问数量
     *
     * @param id 用户 id
     * @return 数据信息
     */
    @RequestMapping(value = "/getUserArticleData", method = RequestMethod.POST)
    public UnifyResponse<ArticleData> getUserArticleData(
            @RequestParam("id") long id) {
        return iArticleService.getUserArticleData(id);
    }

    @RequestMapping(value = "/isFavorite", method = RequestMethod.POST)
    public UnifyResponse<String> isFavorite(
            @RequestParam(value = "articleId") String articleIdStr,
            HttpServletRequest request) {
        String userIdStr = request.getHeader("user_id");
        long[] articleId = idObfuscator.decode(articleIdStr, IdConfig.ARTICLE_ID);
        long userId = 0L;
        if (EmptyCheck.notEmpty(userIdStr) && articleId.length > 0) {
            userId = Long.parseLong(userIdStr);
        } else {
            return UnifyResponse.fail("登录信息或资源错误！", null);
        }
        return iFavoriteService.isFavorite(articleId[0], userId);
    }

    @RequestMapping(value = "/saveIndex", method = RequestMethod.POST)
    public UnifyResponse<String> saveIndex(@RequestParam(value = "id") String id) {
        long[] realId = idObfuscator.decode(id, IdConfig.ARTICLE_ID);
        if (realId.length > 0) {
            return iArticleService.saveIndex(realId[0]);
        }
        return UnifyResponse.fail("id 错误！", null);
    }

    @RequestMapping(value = "/setFavorite", method = RequestMethod.POST)
    public UnifyResponse<String> setFavorite(
            @RequestParam(value = "articleId") String articleIdStr,
            HttpServletRequest request) {
        String userIdStr = request.getHeader("user_id");
        long[] articleId = idObfuscator.decode(articleIdStr, IdConfig.ARTICLE_ID);
        long userId = 0L;
        if (EmptyCheck.notEmpty(userIdStr) && articleId.length > 0) {
            userId = Long.parseLong(userIdStr);
        } else {
            return UnifyResponse.fail("id 错误！", null);
        }
        return iFavoriteService.setFavorite(articleId[0], userId);
    }

    @RequestMapping(value = "/submitAmendmentSuggest", method = RequestMethod.POST)
    public UnifyResponse<String> submitAmendmentSuggest(
            @RequestParam(value = "articleId") String[] articleId,
            @RequestParam(value = "info") String info) {
        return iArticleAmendmentService.submitAmendmentSuggest(articleId, info);
    }

    /**
     * 根据混淆 id 更新文章
     * role:admin
     *
     * @param id      混淆 id
     * @param userId  混淆用户 id
     * @param content 正文
     * @param title   标题
     * @param request 用于获取用户 ip
     * @return 更新结果
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    public UnifyResponse<String> updateArticleById(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "title") String title,
            HttpServletRequest request) {
        // 获取真实 id
        long[] realUserId = idObfuscator.decode(userId, IdConfig.USER_ID);
        long[] realId = idObfuscator.decode(id, IdConfig.ARTICLE_ID);

        if (realUserId.length > 0 && realId.length > 0) {
            //                // 获取当前登录用户 id
            //                long loginId = Long.parseLong((String) StpUtil.getLoginId());
            //                if (loginId == realUserId[0]) {
            return iArticleService.updateArticleById(realId[0], realUserId[0], content, title,
                    // 获取 ip
                    Ip.getIpAddr(request));
            //                }
        }
        return UnifyResponse.fail("id 错误！", null);
    }

    @RequestMapping(value = "/amendment", method = RequestMethod.POST)
    public UnifyResponse<String> updateArticleCount(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "articleId") String articleId,
            @RequestParam(value = "mode") int mode,
            @RequestParam(value = "value") int value,
            @RequestParam(value = "info") String info,
            HttpServletRequest request) {
        long[] loginId = new long[]{0L};
        String userId = request.getHeader("user_id");
        if (EmptyCheck.notEmpty(userId)) {
            loginId[0] = Long.parseLong(userId);
            if (loginId[0] == 1) {
                return iArticleAmendmentService.amendment(id, articleId, mode, value, info);
            }
        }
        return UnifyResponse.fail("id 错误！", null);
    }

    /**
     * role:admin
     *
     * @param content
     * @param id
     * @param ip
     * @param likeNum
     * @param mode
     * @param pageView
     * @param site
     * @param status
     * @param stick
     * @param title
     * @param userId
     * @param createTime
     * @param updateTime
     * @return
     */
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public UnifyResponse<String> updateArticleInfo(
            @RequestParam(value = "content") String content,
            @RequestParam(value = "id") String id,
            @RequestParam(value = "ip") String ip,
            @RequestParam(value = "likeNum") Integer likeNum,
            @RequestParam(value = "mode") Integer mode,
            @RequestParam(value = "pageView") Integer pageView,
            @RequestParam(value = "site") String site,
            @RequestParam(value = "status") Integer status,
            @RequestParam(value = "stick") Integer stick,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "createTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createTime,
            @RequestParam(value = "updateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date updateTime) {
        // 获取真实 id
        long[] realUserId = idObfuscator.decode(userId, IdConfig.USER_ID);
        long[] realId = idObfuscator.decode(id, IdConfig.ARTICLE_ID);
        if (realUserId.length > 0 && realId.length > 0) {
            Article article = new Article();
            article.setId(Math.toIntExact(realId[0]));
            article.setIp(ip);
            article.setLikeNum(likeNum);
            article.setMode(mode);
            article.setPageView(pageView);
            article.setSite(site);
            article.setStatus(status);
            article.setStick(stick);
            article.setTitle(title);
            article.setUserId(Math.toIntExact(realUserId[0]));
            article.setCreateTime(createTime);
            article.setUpdateTime(updateTime);
            return iArticleService.updateArticleById(article);
        }
        return UnifyResponse.fail("id 错误！", null);
    }

    /**
     * 上传文章
     * role:admin
     *
     * @param content 正文
     * @param title   标题
     * @return 上传结果
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public UnifyResponse<String> upload(
            @RequestParam(value = "content") String content,
            @RequestParam(value = "title") String title,
            HttpServletRequest request) {
        // 获取当前登录用户 id
        String userId = request.getHeader("user_id");
        if (EmptyCheck.notEmpty(userId)) {
            long loginId = Long.parseLong(userId);
            return iArticleService.upload(loginId, content, title, Ip.getIpAddr(request));
        }
        return UnifyResponse.fail("用户 id 错误！", null);
    }
}
