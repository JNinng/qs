package top.ninng.qs.article.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Author OhmLaw
 * @Date 2023/3/3 15:23
 * @Version 1.0
 */
public class ArticleAmendmentInfo implements Serializable {

    private static final long serialVersionUID = -7848584300313521031L;

    int page;
    int pageSize;
    int sum;
    ArrayList<ArticleAmendment> list;

    public ArticleAmendmentInfo(int page, int pageSize, int sum, ArrayList<ArticleAmendment> list) {
        this.page = page;
        this.pageSize = pageSize;
        this.sum = sum;
        this.list = list;
    }

    public ArrayList<ArticleAmendment> getList() {
        return list;
    }

    public void setList(ArrayList<ArticleAmendment> list) {
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
}
