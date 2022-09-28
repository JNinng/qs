package com.ninng.qs.user.service;

import com.ninng.qs.user.domain.User;
import com.ninng.qs.user.mapper.ITransactionalTest;
import com.ninng.qs.user.mapper.IUserMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * @Author OhmLaw
 * @Date 2022/9/15 13:38
 * @Version 1.0
 */
@Service
public class UserService {

    private final IUserMapper userMapper;
    private final ITransactionalTest transactionalTest;

    public UserService(IUserMapper userMapper, ITransactionalTest transactionalTest) {
        this.userMapper = userMapper;
        this.transactionalTest = transactionalTest;
    }

    @GlobalTransactional
    public User selectById(Long id) {
        User user;
        user = userMapper.selectById(id);
        transactionalTest.update(id, LocalTime.now().getSecond());
        return user;
    }
}
