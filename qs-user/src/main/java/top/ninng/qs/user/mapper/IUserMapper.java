package top.ninng.qs.user.mapper;

import org.springframework.stereotype.Repository;
import top.ninng.qs.user.entity.User;

import java.util.List;

/**
 * @Author OhmLaw
 * @Date 2022/9/13 22:42
 * @Version 1.0
 */
@Repository("userMapper")
public interface IUserMapper {

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> findAll();

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    User selectById(Long id);
}
