package top.ninng.qs.user.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author OhmLaw
 * @Date 2022/9/13 22:39
 * @Version 1.0
 */
@SpringBootTest
public class User {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void userSelectByIdTest() {
        //        System.out.println(userMapper.selectById(1L));
    }
}
