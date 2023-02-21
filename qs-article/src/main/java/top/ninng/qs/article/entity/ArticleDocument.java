package top.ninng.qs.article.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author OhmLaw
 * @Date 2023/2/21 12:18
 * @Version 1.0
 */
public class ArticleDocument implements Serializable {

    private static final long serialVersionUID = 2851023367114404419L;

    String id;
    String userId;
    String title;
    String content;
    @JSONField(format = "yyyy年MM月")
    Date createTime;
    @JSONField(format = "yyyy年MM月")
    Date updateTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
