package top.ninng.qs.user.entity;

/**
 * 登录响应实体
 *
 * @Author OhmLaw
 * @Date 2023/1/2 18:34
 * @Version 1.0
 */
public class LoginResult {

    String id;
    String token;

    public LoginResult(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "id='" + id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
