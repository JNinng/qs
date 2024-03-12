package top.ninng.qs.article.mapper;

import org.springframework.stereotype.Repository;
import top.ninng.qs.article.entity.ArticleAmendment;

import java.util.ArrayList;

/**
 * @author OhmLaw
 * @description 针对表【article_amendment】的数据库操作Mapper
 * @createDate 2023-03-03 15:20:49
 * @Entity top.ninng.qs.article.entity.ArticleAmendment
 */
@Repository("articleAmendmentMapper")
public interface ArticleAmendmentMapper {

    int deleteByPrimaryKey(Long id);

    /**
     * 分页获取修正信息
     *
     * @param l
     * @param r
     * @return
     */
    ArrayList<ArticleAmendment> getArticleAmendmentByPage(int l, int r);

    int insert(ArticleAmendment record);

    int insertSelective(ArticleAmendment record);

    ArticleAmendment selectByPrimaryKey(Long id);

    /**
     * 查询总条数
     *
     * @return
     */
    int selectCount();

    int updateByPrimaryKey(ArticleAmendment record);

    int updateByPrimaryKeySelective(ArticleAmendment record);

}
