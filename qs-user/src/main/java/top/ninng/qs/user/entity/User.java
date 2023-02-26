package top.ninng.qs.user.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 *
 * @author OhmLaw
 * @TableName user
 */
public class User implements Serializable {

    private static final long serialVersionUID = -5617777527139307890L;

    /**
     * id
     */
    private Integer id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户密码
     */
    @JSONField(serialize = false)
    private String userPassword;
    /**
     * 邮箱
     */
    private String email;
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
     * 审核状态
     */
    private Integer status;
    /**
     * 用户头像
     */
    private String headPortrait;
    /**
     * 用户简介
     */
    private String info;

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
     * 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 用户头像
     */
    public String getHeadPortrait() {
        return headPortrait;
    }

    /**
     * 用户头像
     */
    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
     * 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 用户密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 用户密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserPassword() == null) ? 0 : getUserPassword().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteStatus() == null) ? 0 : getDeleteStatus().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getHeadPortrait() == null) ? 0 : getHeadPortrait().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getInfo() == null) ? 0 : getInfo().hashCode());
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
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserName() == null ? other.getUserName() == null :
                this.getUserName().equals(other.getUserName()))
                && (this.getUserPassword() == null ? other.getUserPassword() == null :
                this.getUserPassword().equals(other.getUserPassword()))
                && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null :
                this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null :
                this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getDeleteStatus() == null ? other.getDeleteStatus() == null :
                this.getDeleteStatus().equals(other.getDeleteStatus()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getHeadPortrait() == null ? other.getHeadPortrait() == null :
                this.getHeadPortrait().equals(other.getHeadPortrait()))
                && (this.getNickname() == null ? other.getNickname() == null :
                this.getNickname().equals(other.getNickname()))
                && (this.getInfo() == null ? other.getInfo() == null :
                this.getInfo().equals(other.getInfo()));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteStatus=" + deleteStatus +
                ", status=" + status +
                ", headPortrait='" + headPortrait + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
