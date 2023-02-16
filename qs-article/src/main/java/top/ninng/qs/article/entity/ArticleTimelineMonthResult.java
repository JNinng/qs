package top.ninng.qs.article.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * 文章时间线，一个月份单位
 *
 * @Author OhmLaw
 * @Date 2023/1/14 14:29
 * @Version 1.0
 */
public class ArticleTimelineMonthResult implements Serializable {

    private static final long serialVersionUID = 1118132096018616369L;

    /**
     * 当前项年月
     */
    @JSONField(format = "yyyy年MM月")
    Date date;

    /**
     * 文章混淆 id 列表
     */
    ArrayList<TimelineMonthItem> idList;

    public ArticleTimelineMonthResult() {
    }

    public ArticleTimelineMonthResult(Date date, ArrayList<TimelineMonthItem> idList) {
        this.date = date;
        this.idList = idList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<TimelineMonthItem> getIdList() {
        return idList;
    }

    public void setIdList(ArrayList<TimelineMonthItem> idList) {
        this.idList = idList;
    }
}
