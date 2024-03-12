package top.ninng.qs.article.service;

import top.ninng.qs.article.entity.*;
import top.ninng.qs.common.entity.UnifyResponse;

import java.util.ArrayList;
import java.util.Date;

/**
 * 文章服务接口
 *
 * @Author OhmLaw
 * @Date 2023/1/6 11:34
 * @Version 1.0
 */
public interface IArticleService {

    UnifyResponse<String> addScore(long id, String mode, String ip, int time);

    /**
     * 根据 id 获取文章
     *
     * @param id 文章 id
     * @return 文章信息
     */
    UnifyResponse<Article> getArticleById(long id);

    /**
     * 根据页数获取文章 id
     *
     * @param page     页数
     * @param pageSize 页大小
     * @return 指定页文章 id
     */
    UnifyResponse<ArticleIdListPageResult> getArticleIdListByPage(int page, int pageSize);

    /**
     * 根据用户 id 获取分页文章信息
     *
     * @param userId   用户 id
     * @param page     页数
     * @param pageSize 页大小
     * @return 分页信息
     */
    UnifyResponse<ArticleIdListPageResult> getArticleIdListByPageAndId(long userId, int page, int pageSize);

    /**
     * 根据 id 获取文章简略信息（无正文）
     *
     * @param id 文章 id
     * @return 指定文章简略信息
     */
    UnifyResponse<Article> getArticleInfoById(long id);

    /**
     * 获取文章无正文信息分页查询
     *
     * @return 文章信息列表
     */
    UnifyResponse<ArrayList<Article>> getArticleListByPage(int page, int pageSize);

    /**
     * 获取指定 id 的文章分页信息
     *
     * @param page     页数
     * @param pageSize 叶大小
     * @param userId   用户 id
     * @return
     */
    UnifyResponse<ArrayList<Article>> getArticleListByPageAndId(int page, int pageSize, long userId);

    /**
     * 根据 id 获取文章预览版
     *
     * @param id 文章 id
     * @return 指定文章预览版
     */
    UnifyResponse<Article> getArticlePreviewById(long id);

    /**
     * 时间线，根据月份查询文章
     *
     * @param date 时间
     * @return 指定月份文章
     */
    UnifyResponse<ArticleTimelineMonthResult> getArticleTimelineMonthResult(Date date);

    /**
     * 获取热榜
     *
     * @return
     */
    UnifyResponse<ArticleIdListPageResult> getHot(int mode, int top);

    /**
     * 查询分页信息
     *
     * @return 分页信息
     */
    UnifyResponse<PageInfo> getPageInfo();

    /**
     * 获取指定用户 id 的文章分页信息
     *
     * @param userId
     * @return
     */
    UnifyResponse<PageInfo> getPageInfoById(long userId);

    /**
     * 根据用户 id 查询文章数据
     *
     * @param id 用户 id
     * @return
     */
    UnifyResponse<ArticleData> getUserArticleData(long id);

    /**
     * 保存指定文章 es 文档
     *
     * @param id
     * @return
     */
    UnifyResponse<String> saveIndex(long id);

    /**
     * 根据 id 更新文章
     *
     * @param id      文章 id
     * @param userId  当前用户 id
     * @param content 正文
     * @param title   标题
     * @param ip      更新请求地 ip 地址
     * @return 更新结果
     */
    UnifyResponse<String> updateArticleById(long id, long userId, String content, String title, String ip);

    /**
     * 根据 id 更新文章无正文信息
     *
     * @param article 文章信息
     * @return 更新结果
     */
    UnifyResponse<String> updateArticleById(Article article);

    /**
     * 上传新文章
     *
     * @param id      用户 id
     * @param content 正文
     * @param title   标题
     * @param ip      ip
     * @return 上传结果
     */
    UnifyResponse<String> upload(long id, String content, String title, String ip);
}
