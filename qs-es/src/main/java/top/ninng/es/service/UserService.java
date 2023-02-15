package top.ninng.es.service;

import top.ninng.es.domain.User;

import java.util.Optional;

/**
 * @Author OhmLaw
 * @Date 2022/9/25 16:52
 * @Version 1.0
 */
public interface UserService {

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Optional<User> findById(String id);
}
