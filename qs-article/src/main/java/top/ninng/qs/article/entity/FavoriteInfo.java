package top.ninng.qs.article.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Author OhmLaw
 * @Date 2023/2/25 15:54
 * @Version 1.0
 */
public class FavoriteInfo implements Serializable {

    private static final long serialVersionUID = -4398817958694614469L;

    /**
     * 总收藏数
     */
    int sum;
    /**
     * 用户 id
     */
    String userId;
    ArrayList<ArticleIdAndTitle> list;
    int page;
    int pageSize;

    public FavoriteInfo(int sum, String userId, ArrayList<ArticleIdAndTitle> list, int page, int pageSize) {
        this.sum = sum;
        this.userId = userId;
        this.list = list;
        this.page = page;
        this.pageSize = pageSize;
    }

    public ArrayList<ArticleIdAndTitle> getList() {
        return list;
    }

    public void setList(ArrayList<ArticleIdAndTitle> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
