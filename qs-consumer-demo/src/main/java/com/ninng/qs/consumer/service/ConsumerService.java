package com.ninng.qs.consumer.service;

import com.ninng.qs.consumer.clients.UserClient;
import com.ninng.qs.consumer.domain.Consumer;
import com.ninng.qs.consumer.domain.ConsumerResult;
import com.ninng.qs.consumer.domain.User;
import com.ninng.qs.consumer.mapper.IConsumerMapper;
import com.ninng.qs.consumer.mapper.ITransactionalTest;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * @Author OhmLaw
 * @Date 2022/9/15 17:25
 * @Version 1.0
 */
@Slf4j
@Service
public class ConsumerService {

    private final UserClient userClient;
    private final IConsumerMapper consumerMapper;
    private final ITransactionalTest transactionalTest;

    public ConsumerService(UserClient userClient, IConsumerMapper consumerMapper,
                           ITransactionalTest transactionalTest) {
        this.userClient = userClient;
        this.consumerMapper = consumerMapper;
        this.transactionalTest = transactionalTest;
    }

    @GlobalTransactional
    public ConsumerResult selectById(Long id) {
        log.warn("日志收集测试！");
        Consumer consumer;
        consumer = consumerMapper.selectById(id);
        transactionalTest.update(id, LocalTime.now().getSecond());
        User user = userClient.selectById(id);
        if (id == 2L) {
            throw new RuntimeException("测试事务异常");
        }
        return new ConsumerResult(consumer, user);
    }
}
