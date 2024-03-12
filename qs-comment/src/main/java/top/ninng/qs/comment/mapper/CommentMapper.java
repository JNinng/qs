package top.ninng.qs.comment.mapper;

import org.springframework.stereotype.Repository;
import top.ninng.qs.comment.entity.Comment;

import java.util.ArrayList;

/**
 * @author OhmLaw
 * @description 针对表【comment(评论信息表)】的数据库操作Mapper
 * @createDate 2023-01-11 16:35:13
 * @Entity top.ninng.qs.common.entity.Comment
 */
@Repository("commentMapper")
public interface CommentMapper {

    /**
     * 根据 id 删除评论
     *
     * @param id 评论 id
     * @return 删除结果
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入一条完整评论
     *
     * @param comment 评论
     * @return 插入结果
     */
    int insert(Comment comment);

    /**
     * 选择性插入一条评论
     *
     * @param comment 评论
     * @return 插入结果
     */
    int insertSelective(Comment comment);

    /**
     * 根据 id 查询评论
     *
     * @param id 评论 id
     * @return 评论信息
     */
    Comment selectByPrimaryKey(Long id);

    /**
     * 根据文章 id 查询评论
     *
     * @param articleId 文章 id
     * @return 指定文章下的评论列表
     */
    ArrayList<Comment> selectCommentByArticleId(Long articleId);

    /**
     * 根据父评论 id 查询子评论
     *
     * @param parentId 父评论 id
     * @return 子评论列表
     */
    ArrayList<Comment> selectCommentByParentId(Long parentId);

    /**
     * 根据用户 id 查询评论
     *
     * @param userId
     * @return
     */
    ArrayList<Comment> selectCommentByUserId(long userId, int l, int r);


    /**
     * 查询用户评论数量
     *
     * @param userId 用户 id
     * @return
     */
    int selectCommentByUserIdCount(long userId);

    /**
     * 根据 id 更新一条评论
     *
     * @param comment 评论
     * @return 更新结果
     */
    int updateByPrimaryKey(Comment comment);

    /**
     * 根据 id 选择性更新一条评论
     *
     * @param comment 评论
     * @return 更新结果
     */
    int updateByPrimaryKeySelective(Comment comment);
}
