package top.ninng.qs.article.mapper;

import org.springframework.stereotype.Repository;
import top.ninng.qs.article.entity.User;

/**
 * @author OhmLaw
 * @description 针对表【user(用户信息表)】的数据库操作Mapper
 * @createDate 2023-01-01 14:20:53
 * @Entity top.ninng.entity.User
 */
@Repository("userMapper")
public interface UserMapper {

    /**
     * 根据 id 删除
     *
     * @param id 用户 id
     * @return 删除结果
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入一条完整用户信息
     *
     * @param user 用户
     * @return 插入结果
     */
    int insert(User user);

    /**
     * 选择性插入
     *
     * @param user 用户
     * @return 插入结果
     */
    int insertSelective(User user);

    /**
     * 根据名称查找用户信息
     *
     * @param name 名称
     * @return 用户
     */
    User selectByName(String name);

    /**
     * 根据 id 查找用户信息
     *
     * @param id 用户 id
     * @return 用户
     */
    User selectByPrimaryKey(Long id);

    /**
     * 更新用户信息
     *
     * @param user 用户
     * @return 更新结果
     */
    int updateByPrimaryKey(User user);

    /**
     * 选择性更新用户信息
     *
     * @param user 用户
     * @return 更新结果
     */
    int updateByPrimaryKeySelective(User user);
}
