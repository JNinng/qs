package top.ninng.qs.article.entity;

import java.io.Serializable;

/**
 * 配置信息
 *
 * @author OhmLaw
 * @TableName config
 */
public class Config implements Serializable {
    private static final long serialVersionUID = -5266453149309811500L;
    /**
     * id
     */
    private Integer id;
    /**
     * 配置key
     */
    private String key;
    /**
     * 配置value
     */
    private String value;
    /**
     * 配置描述
     */
    private String info;
    /**
     * 默认值
     */
    private String defaultValue;

    public Config() {
    }

    public Config(Integer id, String key, String value, String info, String defaultValue) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.info = info;
        this.defaultValue = defaultValue;
    }

    /**
     * 默认值
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * 默认值
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
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
     * 配置描述
     */
    public String getInfo() {
        return info;
    }

    /**
     * 配置描述
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 配置key
     */
    public String getKey() {
        return key;
    }

    /**
     * 配置key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 配置value
     */
    public String getValue() {
        return value;
    }

    /**
     * 配置value
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getKey() == null) ? 0 : getKey().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getInfo() == null) ? 0 : getInfo().hashCode());
        result = prime * result + ((getDefaultValue() == null) ? 0 : getDefaultValue().hashCode());
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
        Config other = (Config) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getKey() == null ? other.getKey() == null : this.getKey().equals(other.getKey()))
                && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
                && (this.getInfo() == null ? other.getInfo() == null : this.getInfo().equals(other.getInfo()))
                && (this.getDefaultValue() == null ? other.getDefaultValue() == null :
                this.getDefaultValue().equals(other.getDefaultValue()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", key=").append(key);
        sb.append(", value=").append(value);
        sb.append(", info=").append(info);
        sb.append(", defaultValue=").append(defaultValue);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
