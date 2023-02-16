package top.ninng.qs.user.entity;

import java.io.Serializable;

/**
 * @Author OhmLaw
 * @Date 2023/2/10 16:13
 * @Version 1.0
 */
public class PageInfo implements Serializable {

    private static final long serialVersionUID = -9223161563322053422L;

    Integer total;

    public PageInfo(Integer total) {
        this.total = total;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
