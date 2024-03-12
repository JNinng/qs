package top.ninng.qs.article.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author OhmLaw
 * @Date 2023/1/23 0:02
 * @Version 1.0
 */
public class ArticleIdAndTitle implements Serializable {

    private static final long serialVersionUID = -8678726361673785005L;

    String id;
    /**
     * 当前项年月
     */
    @JSONField(format = "yyyy年MM月dd日")
    Date date;
    String title;
    String content;

    public ArticleIdAndTitle(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
