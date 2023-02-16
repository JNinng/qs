package top.ninng.qs.comment.service.impl;

import org.springframework.stereotype.Service;
import top.ninng.entity.UnifyResponse;
import top.ninng.qs.comment.config.IdConfig;
import top.ninng.qs.comment.entity.Comment;
import top.ninng.qs.comment.entity.CommentResultItem;
import top.ninng.qs.comment.mapper.CommentMapper;
import top.ninng.qs.comment.service.ICommentService;
import top.ninng.qs.comment.utils.Validator;
import top.ninng.utils.EmptyCheck;
import top.ninng.utils.IdObfuscator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * 评论服务实现类
 *
 * @Author OhmLaw
 * @Date 2023/1/11 18:31
 * @Version 1.0
 */
@Service
public class CommentServiceImpl implements ICommentService {

    CommentMapper commentMapper;
    IdObfuscator idObfuscator;
    int maxCommentLength;
    long defaultCommentId;

    public CommentServiceImpl(CommentMapper commentMapper, IdObfuscator idObfuscator) {
        this.commentMapper = commentMapper;
        this.idObfuscator = idObfuscator;
        // TODO: 从数据库获取配置信息 maxCommentLength = Integer.parseInt(getConfig.map().get("COMMENT_LENGTH"));
        maxCommentLength = 500;
        // TODO: 从数据库中读取游客用户默认 id defaultCommentId = Long.parseLong(getConfig.map().get("DEFAULT_COMMENT_USER_ID"));
        defaultCommentId = 2;
    }

    /**
     * 评论
     *
     * @param name      名称
     * @param email     邮件
     * @param content   中文
     * @param articleId 文章 id
     * @param userId    用户 id
     * @param parentId  父评论 id
     * @param ip        评论请求 ip 地址
     * @return 评论提交结果
     */
    @Override
    public UnifyResponse<String> comment(String name, String email, String content,
                                         Long articleId, Long userId, Long parentId, String ip) {
        if (userId != defaultCommentId) {
            // 登录用户评论处理
            // TODO: userMapper.selectByPrimaryKey(userId);
            name = "默认用户1";
            email = "xx1599xx@163.com";
        }
        if (Validator.isEmail(email)) {
            // 评论长度限制
            if (content.length() <= maxCommentLength) {
                Comment comment = new Comment();
                comment.setName(name);
                comment.setEmail(email);
                comment.setArticleId(Math.toIntExact(articleId));
                comment.setContent(content);
                comment.setUserId(Math.toIntExact(userId));
                comment.setParentId(Math.toIntExact(parentId));
                comment.setSite("localhost");
                comment.setIp(ip);
                comment.setCreateTime(new Timestamp(System.currentTimeMillis()));
                comment.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                // 持久层数据插入
                if ((commentMapper.insertSelective(comment) > 0)) {
                    return UnifyResponse.ok("评论成功！", null);
                }
                return UnifyResponse.ok("评论失败！", null);
            }
            return UnifyResponse.fail("评论过长！", null);
        }
        return UnifyResponse.fail("邮箱错误！", null);
    }

    /**
     * 获取指定评论的子评论
     *
     * @param id 评论 id
     * @return 子评论列表
     */
    @Override
    public UnifyResponse<ArrayList<Comment>> getChildCommentsById(Long id) {
        // 持久层数据查询
        ArrayList<Comment> resultItems = commentMapper.selectCommentByParentId(id);
        if (EmptyCheck.notEmpty(resultItems)) {
            return UnifyResponse.ok(resultItems);
        }
        return UnifyResponse.ok("暂无评论！", null);
    }

    /**
     * 获取指定文章 id 下的评论信息
     *
     * @param articleId 文章 id
     * @return 评论信息列表
     */
    @Override
    public UnifyResponse<ArrayList<CommentResultItem>> getCommentByArticleId(Long articleId) {
        // 持久层数据查询
        ArrayList<Comment> comments = commentMapper.selectCommentByArticleId(articleId);
        //  查询结果处理
        ArrayList<CommentResultItem> commentsResultList = comments.stream()
                // 保留根评论
                .filter(comment -> comment.getParentId() == -1)
                // id 混淆处理
                .peek(this::obfuscatorId)
                .peek(this::setHeadPortrait)
                // 重新封装响应
                .map(comment -> new CommentResultItem(comment, null))
                // 转化输出列表
                .collect(Collectors.toCollection(ArrayList::new));
        // 处理根评论的一级子评论
        commentsResultList = commentsResultList.stream().peek(commentResultItem -> {
            ArrayList<Comment> commentsTemp =
                    commentMapper.selectCommentByParentId(Long.valueOf(commentResultItem.getComment().getId()))
                            .stream()
                            .peek(this::obfuscatorId)
                            .peek(this::setToName)
                            .collect(Collectors.toCollection(ArrayList::new));
            if (EmptyCheck.notEmpty(commentsTemp)) {
                // 赋值子评论列表
                commentResultItem.setChildCommentList(commentsTemp);
            }
        }).collect(Collectors.toCollection(ArrayList::new));
        //        commentsResultList = commentsResultList.stream().peek(commentResultItem -> {
        //            ArrayList<Comment> commentsTemp =
        //                    comments.stream()
        //                            // 在当前文章下所有评论中查询子评论
        //                            .filter(comment -> comment.getParentId().equals(commentResultItem.getComment()
        //                            .getId()))
        //                            // id 混淆处理
        //                            .peek(this::obfuscatorId)
        //                            // 转换输出列表
        //                            .collect(Collectors.toCollection(ArrayList::new));
        //            if (EmptyCheck.notEmpty(commentsTemp)) {
        //                // 赋值子评论列表
        //                commentResultItem.setChildCommentList(commentsTemp);
        //            }
        //        }).collect(Collectors.toCollection(ArrayList::new));
        if (EmptyCheck.notEmpty(comments)) {
            return UnifyResponse.ok(commentsResultList);
        }
        return UnifyResponse.ok("暂无评论", null);
    }

    /**
     * 获取指定 id 的评论信息
     *
     * @param id 评论 id
     * @return 评论信息
     */
    @Override
    public UnifyResponse<CommentResultItem> getCommentById(Long id) {
        // 持久层数据查询当前评论信息
        Comment comment = commentMapper.selectByPrimaryKey(id);
        if (EmptyCheck.notEmpty(comment)) {
            CommentResultItem commentResultItem = new CommentResultItem(comment, null);
            // 查询当前评论的子评论
            ArrayList<Comment> commentList = commentMapper.selectCommentByParentId(Long.valueOf(comment.getId()));
            if (EmptyCheck.notEmpty(commentList)) {
                if (commentList.size() > 0) {
                    // 赋值子评论
                    commentResultItem.setChildCommentList(commentList);
                }
            }
            return UnifyResponse.ok(commentResultItem);
        }
        return UnifyResponse.ok("暂无评论！", null);
    }

    /**
     * 对 id 进行混淆
     *
     * @param comment 评论
     */
    private void obfuscatorId(Comment comment) {
        comment.setObfuscatorId(idObfuscator.encode(comment.getId(), IdConfig.COMMENT_ID));
        comment.setObfuscatorArticleId(idObfuscator.encode(comment.getArticleId(), IdConfig.ARTICLE_ID));
        comment.setObfuscatorParentId(idObfuscator.encode(comment.getParentId(), IdConfig.COMMENT_ID));
        comment.setObfuscatorUserId(idObfuscator.encode(comment.getUserId(), IdConfig.USER_ID));
    }

    /**
     * 设置评论用户头像
     *
     * @param comment 评论
     */
    private void setHeadPortrait(Comment comment) {
        // TODO: 查询评论用户头像 userMapper.selectByPrimaryKey(Long.valueOf(comment.getUserId())).getHeadPortrait()
        comment.setHeadPortrait("问号-1caa408d-3203-4a84-8b28-ea254c94a2be.png");
    }

    /**
     * 设置父评论用户名
     *
     * @param comment
     */
    private void setToName(Comment comment) {
        comment.setToName(commentMapper.selectByPrimaryKey(Long.valueOf(comment.getParentId())).getName());
    }
}
