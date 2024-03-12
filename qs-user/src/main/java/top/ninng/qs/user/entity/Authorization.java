package top.ninng.qs.user.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author OhmLaw
 * @TableName authorization
 */
public class Authorization implements Serializable {

    private static final long serialVersionUID = -8952224227685077853L;

    /**
     * 自增id
     */
    @JSONField(serialize = false)
    private Integer id;
    @JSONField(name = "id")
    private String obfuscatorId;
    /**
     * 授权码
     */
    private String code;
    /**
     * 备注
     */
    private String info;
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 授权码
     */
    public String getCode() {
        return code;
    }

    /**
     * 授权码
     */
    public void setCode(String code) {
        this.code = code;
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
     * 备注
     */
    public String getInfo() {
        return info;
    }

    /**
     * 备注
     */
    public void setInfo(String info) {
        this.info = info;
    }

    public String getObfuscatorId() {
        return obfuscatorId;
    }

    public void setObfuscatorId(String obfuscatorId) {
        this.obfuscatorId = obfuscatorId;
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
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getInfo() == null) ? 0 : getInfo().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getObfuscatorId() == null) ? 0 : getObfuscatorId().hashCode());
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
        Authorization other = (Authorization) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
                && (this.getInfo() == null ? other.getInfo() == null : this.getInfo().equals(other.getInfo()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getObfuscatorId() == null ? other.getObfuscatorId() == null :
                this.getObfuscatorId().equals(other.getObfuscatorId()));
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "id=" + id +
                ", obfuscatorId='" + obfuscatorId + '\'' +
                ", code='" + code + '\'' +
                ", info='" + info + '\'' +
                ", userId=" + userId +
                '}';
    }
}
