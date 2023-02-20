package top.ninng.qs.article.entity;

import java.io.Serializable;

/**
 * 用户文章数据
 *
 * @Author OhmLaw
 * @Date 2023/2/19 11:38
 * @Version 1.0
 */
public class ArticleData implements Serializable {

    private static final long serialVersionUID = 3596635804229035400L;

    /**
     * 文章总数
     */
    private int articleNumber;
    /**
     * 总访问数
     */
    private int pageViewNumber;
    /**
     * 被收藏数
     */
    private int getLikes;

    public int getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    public int getGetLikes() {
        return getLikes;
    }

    public void setGetLikes(int getLikes) {
        this.getLikes = getLikes;
    }

    public int getPageViewNumber() {
        return pageViewNumber;
    }

    public void setPageViewNumber(int pageViewNumber) {
        this.pageViewNumber = pageViewNumber;
    }
}
