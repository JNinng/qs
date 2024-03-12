package top.ninng.qs.article.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 文章列表页响应实体
 *
 * @Author OhmLaw
 * @Date 2023/1/7 21:00
 * @Version 1.0
 */
public class ArticleIdListPageResult implements Serializable {

    private static final long serialVersionUID = -1575787375613628531L;

    /**
     * id
     */
    private ArrayList<ArticleIdAndTitle> list;
    /**
     * 页数
     */
    private int page;
    /**
     * 页大小
     */
    private int pageSize;

    public ArticleIdListPageResult(ArrayList<ArticleIdAndTitle> list, int page, int pageSize) {
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
}
