package top.ninng.qs.article.mapper;

import org.springframework.stereotype.Repository;
import top.ninng.qs.article.entity.Article;
import top.ninng.qs.article.entity.ArticleIdAndTitle;
import top.ninng.qs.article.entity.TimelineMonthItem;

import java.util.ArrayList;

/**
 * @author OhmLaw
 * @description 针对表【article(文章信息表)】的数据库操作Mapper
 * @createDate 2023-01-06 16:51:55
 * @Entity top.ninng.qs.common.entity.Article
 */
@Repository("articleMapper")
public interface ArticleMapper {

    /**
     * 根据 id 删除文章
     *
     * @param id 文章 id
     * @return 删除结果
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据年月查询文章
     *
     * @param year  年
     * @param month 月
     * @return id 列表
     */
    ArrayList<TimelineMonthItem> getArticleIdListByMonth(String year, String month);

    /**
     * 插入完整文章信息
     *
     * @param article 文章
     * @return 插入结果
     */
    int insert(Article article);

    /**
     * 选择性插入文章信息
     *
     * @param article 文章
     * @return 插入结果
     */
    int insertSelective(Article article);

    /**
     * 分页查询文章无正文信息列表
     *
     * @param l 左边界
     * @param r 右边界
     * @return 文章信息列表
     */
    ArrayList<Article> selectArticleByPage(int l, int r);

    ArrayList<Article> selectArticleDocumentByPage(int l, int r);

    /**
     * 分页查询文章 id
     *
     * @param l 左边界
     * @param r 右边界
     * @return 分页查询 id 结果列表
     */
    ArrayList<Long> selectArticleIdListByPage(int l, int r);

    /**
     * 查询分页信息
     *
     * @return 文章分页信息
     */
    int selectArticleTotal();

    /**
     * 根据 id 查询文章信息
     *
     * @param id 文章 id
     * @return 文章信息
     */
    Article selectByPrimaryKey(Long id);

    int selectCount();

    /**
     * 根据文章 id 查询标题
     *
     * @param id 文章 id
     * @return 文章标题
     */
    ArticleIdAndTitle selectTitleAndDateByPrimaryKey(Long id);

    /**
     * 根据 id 查找所属用户
     *
     * @param id 文章 id
     * @return 所属用户 id
     */
    long selectUserIdByIdInt(Long id);

    /**
     * 更新完整文章信息
     *
     * @param article 文章
     * @return 更新结果
     */
    int updateByPrimaryKey(Article article);

    /**
     * 选择性更新文章信息
     *
     * @param article 文章
     * @return 更新结果
     */
    int updateByPrimaryKeySelective(Article article);
}
