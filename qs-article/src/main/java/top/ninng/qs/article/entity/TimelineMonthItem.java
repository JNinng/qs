package top.ninng.qs.article.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author OhmLaw
 * @Date 2023/1/19 10:12
 * @Version 1.0
 */
public class TimelineMonthItem implements Serializable {

    private static final long serialVersionUID = -223105218646675167L;

    @JSONField(serialize = false)
    Integer id;
    @JSONField(name = "id")
    String obfuscatorId;
    String title;
    @JSONField(format = "MM-dd")
    Date date;

    public TimelineMonthItem() {
    }

    public TimelineMonthItem(Integer id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObfuscatorId() {
        return obfuscatorId;
    }

    public void setObfuscatorId(String obfuscatorId) {
        this.obfuscatorId = obfuscatorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
