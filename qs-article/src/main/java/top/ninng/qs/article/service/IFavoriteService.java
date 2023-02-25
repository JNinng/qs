package top.ninng.qs.article.service;

import top.ninng.qs.article.entity.FavoriteInfo;
import top.ninng.qs.common.entity.UnifyResponse;

/**
 * @Author OhmLaw
 * @Date 2023/2/25 16:10
 * @Version 1.0
 */
public interface IFavoriteService {

    /**
     * 取消收藏文章
     *
     * @param articleId 文章 id
     * @param userId    用户 id
     * @return 取消收藏结果
     */
    UnifyResponse<String> deleteFavorite(long articleId, long userId);

    /**
     * 分页获取用户收藏列表
     *
     * @param userId   用户 id
     * @param page     页数
     * @param pageSize 页大小
     * @return
     */
    UnifyResponse<FavoriteInfo> getFavorite(long userId, int page, int pageSize);

    /**
     * 收藏文章
     *
     * @param articleId 文章 id
     * @param userId    用户 id
     * @return 收藏结果
     */
    UnifyResponse<String> setFavorite(long articleId, long userId);
}
