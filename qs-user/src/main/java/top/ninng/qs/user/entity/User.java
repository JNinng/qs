package top.ninng.qs.user.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author OhmLaw
 * @Date 2022/9/13 22:43
 * @Version 1.0
 */
@Data
public class User implements Serializable {

    private Integer id;
    private String name;
    private String nickname;
    private String password;
}
