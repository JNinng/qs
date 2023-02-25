package top.ninng.qs.user.entity;

import java.io.Serializable;

/**
 * 用户关系表，a关注（或其他）b的关系
 *
 * @author OhmLaw
 * @TableName relation
 */
public class Relation implements Serializable {

    private static final long serialVersionUID = 5933058327247153274L;

    /**
     * 主键 id
     */
    private Integer id;

    /**
     * 用户a
     */
    private Integer aUserId;

    /**
     * 用户b
     */
    private Integer bUserId;


    /**
     * 主键 id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键 id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 用户a
     */
    public Integer getaUserId() {
        return aUserId;
    }

    /**
     * 用户a
     */
    public void setaUserId(Integer aUserId) {
        this.aUserId = aUserId;
    }

    /**
     * 用户b
     */
    public Integer getbUserId() {
        return bUserId;
    }

    /**
     * 用户b
     */
    public void setbUserId(Integer bUserId) {
        this.bUserId = bUserId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getaUserId() == null) ? 0 : getaUserId().hashCode());
        result = prime * result + ((getbUserId() == null) ? 0 : getbUserId().hashCode());
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
        Relation other = (Relation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getaUserId() == null ? other.getaUserId() == null :
                this.getaUserId().equals(other.getaUserId()))
                && (this.getbUserId() == null ? other.getbUserId() == null :
                this.getbUserId().equals(other.getbUserId()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", aUserId=").append(aUserId);
        sb.append(", bUserId=").append(bUserId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
