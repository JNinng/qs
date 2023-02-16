package top.ninng.qs.article.mapper;

import org.springframework.stereotype.Repository;
import top.ninng.qs.article.entity.ArticleTag;

import java.util.ArrayList;

/**
 * @author OhmLaw
 * @description 针对表【article_tag(文章标签信息表)】的数据库操作Mapper
 * @createDate 2023-01-13 19:21:58
 * @Entity top.ninng.entity.ArticleTag
 */
@Repository("articleTagMapper")
public interface ArticleTagMapper {

    /**
     * 根据 id 删除文章中额标签
     *
     * @param id 文章标签 id
     * @return 删除结果
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入一条文章标签关系
     *
     * @param articleTag 文章标签关系
     * @return 插入结果
     */
    int insert(ArticleTag articleTag);

    /**
     * 选择性插入一条文章标签关系
     *
     * @param record 文章标签关系
     * @return 插入结果
     */
    int insertSelective(ArticleTag record);

    /**
     * 根据标签 id 分页查询
     *
     * @param id 标签 id
     * @param l  左边界
     * @param r  右边界
     * @return 分页查询 id 结果列表
     */
    ArrayList<Long> selectArticleIdListByPageByName(Long id, int l, int r);

    /**
     * 根据文章标签关系 id 查询
     *
     * @param id 文章标签关系 id
     * @return 查询结果
     */
    ArticleTag selectByPrimaryKey(Long id);

    /**
     * 根据标签 id 查询所标记的文章数
     *
     * @param tagId 标签 id
     * @return 标签标记的文章数量
     */
    int selectCountByTag(Long tagId);

    /**
     * 根据 id 更新文章标签关系
     *
     * @param record 文章标签关系 id
     * @return 更新结果
     */
    int updateByPrimaryKey(ArticleTag record);

    /**
     * 根据 id 选择性更新文章标签关系
     *
     * @param record 文章标签关系 id
     * @return 更新结果
     */
    int updateByPrimaryKeySelective(ArticleTag record);
}
