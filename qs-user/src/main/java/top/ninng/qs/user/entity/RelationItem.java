package top.ninng.qs.user.entity;

import java.io.Serializable;

/**
 * @Author OhmLaw
 * @Date 2023/2/24 22:44
 * @Version 1.0
 */
public class RelationItem implements Serializable {

    private static final long serialVersionUID = 5937670790582997585L;

    String id;
    String name;
    String headPortrait;

    public RelationItem(String id, String name, String headPortrait) {
        this.id = id;
        this.name = name;
        this.headPortrait = headPortrait;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
