package top.ninng.qs.article.mapper;

import org.springframework.stereotype.Repository;
import top.ninng.qs.article.entity.Favorite;

import java.util.ArrayList;

/**
 * @author OhmLaw
 * @description 针对表【favorite】的数据库操作Mapper
 * @createDate 2023-02-25 15:49:24
 * @Entity top.ninng.qs.article.entity.Favorite
 */
@Repository("favoriteMapper")
public interface FavoriteMapper {

    /**
     * 根据 id 取消收藏
     *
     * @param id 关系 id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 取消用户对文章的收藏
     *
     * @param articleId 文章 id
     * @param userId    用户 id
     * @return 取消结果
     */
    int deleteFavorite(Long articleId, Long userId);

    /**
     * 获取用户收藏分页信息
     *
     * @param userId 用户 id
     * @param l      左边界
     * @param r      右边界
     * @return 分页
     */
    ArrayList<Long> getFavorite(Long userId, int l, int r);

    /**
     * 插入一条收藏
     *
     * @param favorite 收藏
     * @return 插入结果
     */
    int insert(Favorite favorite);

    /**
     * 选择性插入
     *
     * @param favorite 收藏关系
     * @return
     */
    int insertSelective(Favorite favorite);

    /**
     * 根据 id 查找
     *
     * @param id
     * @return
     */
    Favorite selectByPrimaryKey(Long id);

    /**
     * 查询用户收藏数量
     *
     * @param userId 用户 id
     * @return 结果
     */
    int selectFavoriteCount(Long userId);

    /**
     * 根据 id 更新
     *
     * @param favorite 收藏关系
     * @return
     */
    int updateByPrimaryKey(Favorite favorite);

    /**
     * 选择性更新
     *
     * @param favorite 收藏关系
     * @return
     */
    int updateByPrimaryKeySelective(Favorite favorite);

}
