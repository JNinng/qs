package top.ninng.qs.comment.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论信息表
 *
 * @author OhmLaw
 * @TableName comment
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = -356080986565656913L;

    /**
     * 评论 id
     */
    @JSONField(serialize = false)
    private Integer id;

    /**
     * 混淆评论 id
     */
    @JSONField(name = "id")
    private String obfuscatorId;
    /**
     * 用户 id
     */
    @JSONField(serialize = false)
    private Integer userId;
    /**
     * 混淆用户 id
     */
    @JSONField(name = "userId")
    private String obfuscatorUserId;
    /**
     * 评论文章 id
     */
    @JSONField(serialize = false)
    private Integer articleId;
    /**
     * 混淆文章 id
     */
    @JSONField(name = "articleId")
    private String obfuscatorArticleId;
    /**
     * 父评论 id
     */
    @JSONField(serialize = false)
    private Integer parentId;
    /**
     * 混淆父评论 id
     */
    @JSONField(name = "parentId")
    private String obfuscatorParentId;
    /**
     * 父评论用户名
     */
    private String toName;
    /**
     * 用户头像
     */
    private String headPortrait;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 删除状态
     */
    private Boolean deleteStatus;
    /**
     * 评论审核状态
     */
    private Integer status;
    /**
     * 评论所有者名称
     */
    private String name;
    /**
     * 评论所有者邮箱
     */
    private String email;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 发布地点
     */
    private String site;
    /**
     * 发布ip
     */
    private String ip;

    /**
     * 评论文章id
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * 评论文章id
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 评论内容
     */
    public void setContent(String content) {
        this.content = content;
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
     * 删除状态
     */
    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 删除状态
     */
    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * 评论所有者邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 评论所有者邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    /**
     * 评论id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 评论id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 发布ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 发布ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 评论所有者名称
     */
    public String getName() {
        return name;
    }

    /**
     * 评论所有者名称
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getObfuscatorArticleId() {
        return obfuscatorArticleId;
    }

    public void setObfuscatorArticleId(String obfuscatorArticleId) {
        this.obfuscatorArticleId = obfuscatorArticleId;
    }

    public String getObfuscatorId() {
        return obfuscatorId;
    }

    public void setObfuscatorId(String obfuscatorId) {
        this.obfuscatorId = obfuscatorId;
    }

    public String getObfuscatorParentId() {
        return obfuscatorParentId;
    }

    public void setObfuscatorParentId(String obfuscatorParentId) {
        this.obfuscatorParentId = obfuscatorParentId;
    }

    public String getObfuscatorUserId() {
        return obfuscatorUserId;
    }

    public void setObfuscatorUserId(String obfuscatorUserId) {
        this.obfuscatorUserId = obfuscatorUserId;
    }

    /**
     * 父评论id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 父评论id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 发布地点
     */
    public String getSite() {
        return site;
    }

    /**
     * 发布地点
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * 评论审核状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 评论审核状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    /**
     * 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteStatus() == null) ? 0 : getDeleteStatus().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getSite() == null) ? 0 : getSite().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getHeadPortrait() == null) ? 0 : getHeadPortrait().hashCode());
        result = prime * result + ((getToName() == null) ? 0 : getToName().hashCode());
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
        Comment other = (Comment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getArticleId() == null ? other.getArticleId() == null :
                this.getArticleId().equals(other.getArticleId()))
                && (this.getParentId() == null ? other.getParentId() == null :
                this.getParentId().equals(other.getParentId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null :
                this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null :
                this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getDeleteStatus() == null ? other.getDeleteStatus() == null :
                this.getDeleteStatus().equals(other.getDeleteStatus()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
                && (this.getContent() == null ? other.getContent() == null :
                this.getContent().equals(other.getContent()))
                && (this.getSite() == null ? other.getSite() == null : this.getSite().equals(other.getSite()))
                && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
                && (this.getHeadPortrait() == null ? other.getHeadPortrait() == null :
                this.getHeadPortrait().equals(other.getHeadPortrait()))
                && (this.getToName() == null ? other.getToName() == null : this.getToName().equals(other.getToName()));
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", obfuscatorId='" + obfuscatorId + '\'' +
                ", userId=" + userId +
                ", obfuscatorUserId='" + obfuscatorUserId + '\'' +
                ", articleId=" + articleId +
                ", obfuscatorArticleId='" + obfuscatorArticleId + '\'' +
                ", parentId=" + parentId +
                ", obfuscatorParentId='" + obfuscatorParentId + '\'' +
                ", toName='" + toName + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteStatus=" + deleteStatus +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", site='" + site + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
