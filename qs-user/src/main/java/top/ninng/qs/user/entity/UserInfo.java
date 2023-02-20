package top.ninng.qs.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author OhmLaw
 * @Date 2023/2/17 16:19
 * @Version 1.0
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -8791290190427080557L;

    private String id;
    private String name;
    private String nickname;
    private String email;
    private String info;
    private Date createTime;
    private String headPortrait;
    private String site;
    private int articleNumber;
    private int followNumber;
    private int fansNumber;
    private int pageViewNumber;
    private int getLikes;

    public int getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(int fansNumber) {
        this.fansNumber = fansNumber;
    }

    public int getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(int followNumber) {
        this.followNumber = followNumber;
    }

    public int getGetLikes() {
        return getLikes;
    }

    public void setGetLikes(int getLikes) {
        this.getLikes = getLikes;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPageViewNumber() {
        return pageViewNumber;
    }

    public void setPageViewNumber(int pageViewNumber) {
        this.pageViewNumber = pageViewNumber;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", info='" + info + '\'' +
                ", createTime=" + createTime +
                ", headPortrait='" + headPortrait + '\'' +
                ", site='" + site + '\'' +
                ", articleNumber=" + articleNumber +
                ", followNumber=" + followNumber +
                ", fansNumber=" + fansNumber +
                ", pageViewNumber=" + pageViewNumber +
                ", getLikes=" + getLikes +
                '}';
    }
}
