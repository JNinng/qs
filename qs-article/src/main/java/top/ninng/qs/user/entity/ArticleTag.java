package top.ninng.qs.user.entity;

import java.io.Serializable;

/**
 * 文章标签信息表
 *
 * @author OhmLaw
 * @TableName article_tag
 */
public class ArticleTag implements Serializable {

    private static final long serialVersionUID = -7379496019679864103L;

    /**
     * id
     */
    private Integer id;
    /**
     * 文章id
     */
    private Integer articleId;
    /**
     *
     */
    private Integer tagId;

    /**
     * 文章id
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * 文章id
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * id
     */
    public Integer getId() {
        return id;
    }

    /**
     * id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     */
    public Integer getTagId() {
        return tagId;
    }

    /**
     *
     */
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getTagId() == null) ? 0 : getTagId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ArticleTag other = (ArticleTag) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getArticleId() == null ? other.getArticleId() == null :
                this.getArticleId().equals(other.getArticleId()))
                && (this.getTagId() == null ? other.getTagId() == null : this.getTagId().equals(other.getTagId()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", articleId=").append(articleId);
        sb.append(", tagId=").append(tagId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
