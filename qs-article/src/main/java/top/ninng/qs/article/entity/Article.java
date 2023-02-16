package top.ninng.qs.article.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章信息表
 *
 * @author OhmLaw
 * @TableName article
 */
public class Article implements Serializable {

    private static final long serialVersionUID = 5699480760312845950L;

    /**
     * 文章id
     */
    @JSONField(serialize = false)
    private Integer id;
    /**
     * 混淆文章id
     */
    @JSONField(name = "id")
    private String obfuscatorId;
    /**
     * 所属用户id
     */
    @JSONField(serialize = false)
    private Integer userId;
    /**
     * 混淆用户id
     */
    @JSONField(name = "userId")
    private String obfuscatorUserId;
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
    @JSONField(serialize = false)
    private Boolean deleteStatus;
    /**
     * md原文
     */
    private String content;
    /**
     * 标题
     */
    private String title;
    /**
     * 访问量
     */
    private Integer pageView;
    /**
     * 点赞数
     */
    private Integer likeNum;
    /**
     * 审核状态
     */
    private Integer status;
    /**
     * 置顶
     */
    private Integer stick;
    /**
     * 发布地点
     */
    private String site;
    /**
     * 发布ip
     */
    private String ip;
    /**
     * 是否有预览内容
     */
    private Integer mode;

    /**
     * md原文
     */
    public String getContent() {
        return content;
    }

    /**
     * md原文
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
     * 文章id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 文章id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 点赞数
     */
    public Integer getLikeNum() {
        return likeNum;
    }

    /**
     * 点赞数
     */
    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    /**
     * 是否有预览内容
     */
    public Integer getMode() {
        return mode;
    }

    /**
     * 是否有预览内容
     */
    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public String getObfuscatorId() {
        return obfuscatorId;
    }

    public void setObfuscatorId(String obfuscatorId) {
        this.obfuscatorId = obfuscatorId;
    }

    public String getObfuscatorUserId() {
        return obfuscatorUserId;
    }

    public void setObfuscatorUserId(String obfuscatorUserId) {
        this.obfuscatorUserId = obfuscatorUserId;
    }

    /**
     * 访问量
     */
    public Integer getPageView() {
        return pageView;
    }

    /**
     * 访问量
     */
    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    /**
     * 审核状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 审核状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 置顶
     */
    public Integer getStick() {
        return stick;
    }

    /**
     * 置顶
     */
    public void setStick(Integer stick) {
        this.stick = stick;
    }

    /**
     * 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     */
    public void setTitle(String title) {
        this.title = title;
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
     * 所属用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 所属用户id
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
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteStatus() == null) ? 0 : getDeleteStatus().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getPageView() == null) ? 0 : getPageView().hashCode());
        result = prime * result + ((getLikeNum() == null) ? 0 : getLikeNum().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getStick() == null) ? 0 : getStick().hashCode());
        result = prime * result + ((getSite() == null) ? 0 : getSite().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getMode() == null) ? 0 : getMode().hashCode());
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
        Article other = (Article) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null :
                this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null :
                this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getDeleteStatus() == null ? other.getDeleteStatus() == null :
                this.getDeleteStatus().equals(other.getDeleteStatus()))
                && (this.getContent() == null ? other.getContent() == null :
                this.getContent().equals(other.getContent()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getPageView() == null ? other.getPageView() == null :
                this.getPageView().equals(other.getPageView()))
                && (this.getLikeNum() == null ? other.getLikeNum() == null :
                this.getLikeNum().equals(other.getLikeNum()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getStick() == null ? other.getStick() == null : this.getStick().equals(other.getStick()))
                && (this.getSite() == null ? other.getSite() == null : this.getSite().equals(other.getSite()))
                && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
                && (this.getMode() == null ? other.getMode() == null : this.getMode().equals(other.getMode()));
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", obfuscatorId='" + obfuscatorId + '\'' +
                ", userId=" + userId +
                ", obfuscatorUserId='" + obfuscatorUserId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteStatus=" + deleteStatus +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", pageView=" + pageView +
                ", likeNum=" + likeNum +
                ", status=" + status +
                ", stick=" + stick +
                ", site='" + site + '\'' +
                ", ip='" + ip + '\'' +
                ", mode=" + mode +
                '}';
    }
}
