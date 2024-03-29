package top.ninng.es.service;

import top.ninng.es.entity.ArticleDocument;
import top.ninng.es.entity.User;
import top.ninng.qs.common.entity.UnifyResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * @Author OhmLaw
 * @Date 2022/9/25 16:52
 * @Version 1.0
 */
public interface IndexDatabaseService {

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Optional<User> findById(String id);

    /**
     * 获取相似文章
     *
     * @param key
     * @return
     */
    UnifyResponse<ArrayList<ArticleDocument>> getLikeArticle(String key, int page, int pageSize, String articleId);

    void save();

    void saveArticle(String id, String userId, String title, String content, Date createTime, Date updateTime);

    /**
     * 文章搜索
     *
     * @param key
     * @return
     */
    UnifyResponse<ArrayList<ArticleDocument>> searchArticle(String key, int page, int pageSize);

    /**
     * 更新文章文档信息
     *
     * @param articleId
     * @param count
     * @return
     */
    UnifyResponse<String> updateArticleCount(String articleId, int count);
}
