package top.ninng.qs.comment.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 评论响应实体
 *
 * @Author OhmLaw
 * @Date 2023/1/11 19:21
 * @Version 1.0
 */
public class CommentResultItem implements Serializable {

    private static final long serialVersionUID = 6917470717255079138L;

    Comment comment;
    ArrayList<Comment> childCommentList;
    int parentCount;

    public CommentResultItem(Comment comment, ArrayList<Comment> childCommentList) {
        this.comment = comment;
        this.childCommentList = childCommentList;
    }

    public ArrayList<Comment> getChildCommentList() {
        return childCommentList;
    }

    public void setChildCommentList(ArrayList<Comment> childCommentList) {
        this.childCommentList = childCommentList;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public int getParentCount() {
        return parentCount;
    }

    public void setParentCount(int parentCount) {
        this.parentCount = parentCount;
    }

    @Override
    public String toString() {
        return "CommentResultItem{" +
                "comment=" + comment +
                ", childComment=" + childCommentList +
                '}';
    }
}
