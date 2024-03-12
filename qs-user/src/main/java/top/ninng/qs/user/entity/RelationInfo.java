package top.ninng.qs.user.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 关系分页信息
 *
 * @Author OhmLaw
 * @Date 2023/2/24 22:34
 * @Version 1.0
 */
public class RelationInfo implements Serializable {

    private static final long serialVersionUID = 7176892175695347011L;
    /**
     * 关系总数，包含其它位查询页
     */
    int sum;
    String userId;
    ArrayList<RelationItem> userList;
    int page;
    int pageSize;

    public RelationInfo(int sum, String userId, ArrayList<RelationItem> userList, int page, int pageSize) {
        this.sum = sum;
        this.userId = userId;
        this.userList = userList;
        this.page = page;
        this.pageSize = pageSize;
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

    public ArrayList<RelationItem> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<RelationItem> userList) {
        this.userList = userList;
    }
}
