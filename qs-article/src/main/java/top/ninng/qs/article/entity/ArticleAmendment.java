package top.ninng.qs.article.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author OhmLaw
 * @TableName article_amendment
 */
public class ArticleAmendment implements Serializable {

    private static final long serialVersionUID = -4350623764286579098L;

    /**
     * 自增id
     */
    private Integer id;

    /**
     *
     */
    private String articleId;

    /**
     * 备注信息
     */
    private String info;

    /**
     * 处理信息
     */
    private String dispose;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     *
     */
    private Boolean deleteStatus;

    /**
     *
     */
    private Integer type;

    /**
     *
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     *
     */
    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     */
    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    /**
     *
     */
    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * 处理信息
     */
    public String getDispose() {
        return dispose;
    }

    /**
     * 处理信息
     */
    public void setDispose(String dispose) {
        this.dispose = dispose;
    }

    /**
     * 自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 自增id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 备注信息
     */
    public String getInfo() {
        return info;
    }

    /**
     * 备注信息
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     *
     */
    public Integer getType() {
        return type;
    }

    /**
     *
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getInfo() == null) ? 0 : getInfo().hashCode());
        result = prime * result + ((getDispose() == null) ? 0 : getDispose().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteStatus() == null) ? 0 : getDeleteStatus().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
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
        ArticleAmendment other = (ArticleAmendment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getArticleId() == null ? other.getArticleId() == null :
                this.getArticleId().equals(other.getArticleId()))
                && (this.getInfo() == null ? other.getInfo() == null : this.getInfo().equals(other.getInfo()))
                && (this.getDispose() == null ? other.getDispose() == null :
                this.getDispose().equals(other.getDispose()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null :
                this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null :
                this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getDeleteStatus() == null ? other.getDeleteStatus() == null :
                this.getDeleteStatus().equals(other.getDeleteStatus()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", articleId=").append(articleId);
        sb.append(", info=").append(info);
        sb.append(", dispose=").append(dispose);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
