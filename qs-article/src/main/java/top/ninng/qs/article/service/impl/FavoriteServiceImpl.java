package top.ninng.qs.article.service.impl;

import org.springframework.stereotype.Service;
import top.ninng.qs.article.config.IdConfig;
import top.ninng.qs.article.entity.Article;
import top.ninng.qs.article.entity.ArticleIdAndTitle;
import top.ninng.qs.article.entity.Favorite;
import top.ninng.qs.article.entity.FavoriteInfo;
import top.ninng.qs.article.mapper.ArticleMapper;
import top.ninng.qs.article.mapper.FavoriteMapper;
import top.ninng.qs.article.service.IFavoriteService;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.common.utils.EmptyCheck;
import top.ninng.qs.common.utils.IdObfuscator;

import java.util.ArrayList;

/**
 * @Author OhmLaw
 * @Date 2023/2/25 16:17
 * @Version 1.0
 */
@Service
public class FavoriteServiceImpl implements IFavoriteService {

    FavoriteMapper favoriteMapper;
    ArticleMapper articleMapper;
    IdObfuscator idObfuscator;

    public FavoriteServiceImpl(FavoriteMapper favoriteMapper, ArticleMapper articleMapper, IdObfuscator idObfuscator) {
        this.favoriteMapper = favoriteMapper;
        this.articleMapper = articleMapper;
        this.idObfuscator = idObfuscator;
    }

    @Override
    public UnifyResponse<String> deleteFavorite(long articleId, long userId) {
        favoriteMapper.deleteFavorite(articleId, userId);
        return UnifyResponse.ok("取消收藏成功！", null);
    }

    @Override
    public UnifyResponse<FavoriteInfo> getFavorite(long userId, int page, int pageSize) {
        // 分页逻辑处理
        page = (page < 0) ? 0 : page;
        pageSize = (pageSize <= 0) ? 10 : pageSize;
        int count = favoriteMapper.selectFavoriteCount(userId);
        ArrayList<Long> longArrayList = favoriteMapper.getFavorite(userId, (page - 1) * pageSize, pageSize);
        ArrayList<ArticleIdAndTitle> articleList = new ArrayList<>();
        longArrayList.forEach(aLong -> {
            ArticleIdAndTitle articleIdAndTitle = articleMapper.selectTitleAndDateByPrimaryKey(aLong);
            if (EmptyCheck.notEmpty(articleIdAndTitle)) {
                articleIdAndTitle.setId(idObfuscator.encode(Math.toIntExact(aLong), IdConfig.ARTICLE_ID));
                articleList.add(articleIdAndTitle);
            }
        });
        return UnifyResponse.ok(
                new FavoriteInfo(
                        count,
                        idObfuscator.encode(Math.toIntExact(userId), IdConfig.USER_ID),
                        articleList,
                        page,
                        pageSize));
    }

    @Override
    public UnifyResponse<String> isFavorite(Long articleId, Long userId) {
        int result = favoriteMapper.selectFavorite(articleId, userId);
        if (result > 0) {
            return UnifyResponse.ok("已收藏！", null);
        }
        return UnifyResponse.fail("未收藏！", null);
    }

    @Override
    public UnifyResponse<String> setFavorite(long articleId, long userId) {
        Article article = articleMapper.selectByPrimaryKey(articleId);
        if (EmptyCheck.isEmpty(article)) {
            return UnifyResponse.fail("资源不存在！", null);
        }
        Favorite favorite = new Favorite();
        favorite.setArticleId(Math.toIntExact(articleId));
        favorite.setUserId(Math.toIntExact(userId));
        int insert = favoriteMapper.insert(favorite);
        if (insert > 0) {
            return UnifyResponse.ok("收藏成功！", null);
        }
        return UnifyResponse.fail("收藏失败！", null);
    }
}
