package top.ninng.qs.article.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import top.ninng.entity.UnifyResponse;
import top.ninng.qs.article.config.IdConfig;
import top.ninng.qs.article.entity.Article;
import top.ninng.qs.article.entity.ArticleIdListPageResult;
import top.ninng.qs.article.entity.ArticleTimelineMonthResult;
import top.ninng.qs.article.entity.PageInfo;
import top.ninng.qs.article.service.IArticleService;
import top.ninng.qs.article.utils.Ip;
import top.ninng.utils.IdObfuscator;

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
    IdObfuscator idObfuscator;

    public ArticleController(IArticleService iArticleService, IdObfuscator idObfuscator) {
        this.iArticleService = iArticleService;
        this.idObfuscator = idObfuscator;
        System.out.println(idObfuscator.encode(1, IdConfig.ARTICLE_ID));
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
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize) {
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
     *
     * @return 文章信息列表
     */
    @RequestMapping(value = "/getPageList", method = RequestMethod.POST)
    public UnifyResponse<ArrayList<Article>> getArticleListByPage(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize) {
        if (StpUtil.isLogin()) {
            return iArticleService.getArticleListByPage(page, pageSize);
        }
        return UnifyResponse.fail("认证失败！", null);
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

    /**
     * 获取文章管理的分页信息
     *
     * @return 文章管理部分分页信息
     */
    @RequestMapping(value = "/pageInfo", method = RequestMethod.GET)
    public UnifyResponse<PageInfo> getPageInfo() {
        if (StpUtil.isLogin()) {
            return iArticleService.getPageInfo();
        }
        return UnifyResponse.fail("认证失败！", null);
    }

    /**
     * 根据混淆 id 更新文章
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
        // 判断是否登录
        if (StpUtil.isLogin()) {
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
        return UnifyResponse.fail("您还未登录！", null);
    }

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
        if (StpUtil.isLogin()) {
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
        }
        return UnifyResponse.fail("认证失败！", null);
    }

    /**
     * 上传文章
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
        if (StpUtil.isLogin()) {
            // 获取当前登录用户 id
            long loginId = Long.parseLong((String) StpUtil.getLoginId());
            return iArticleService.upload(loginId, content, title, Ip.getIpAddr(request));
        }
        return UnifyResponse.fail("您还未登录！");
    }
}
