package com.ninng.qs.consumer.mapper;

import com.ninng.qs.consumer.domain.Consumer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author OhmLaw
 * @Date 2022/9/19 11:37
 * @Version 1.0
 */
@Repository("consumerMapper")
public interface IConsumerMapper {

    /**
     * 查询所有消费信息
     *
     * @return
     */
    List<Consumer> findAll();

    /**
     * 根据ID查询消费信息
     *
     * @param id
     * @return
     */
    Consumer selectById(Long id);
}
