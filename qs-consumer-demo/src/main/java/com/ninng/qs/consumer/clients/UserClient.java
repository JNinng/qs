package com.ninng.qs.consumer.clients;

import com.ninng.qs.consumer.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author OhmLaw
 * @Date 2022/9/15 17:08
 * @Version 1.0
 */
@FeignClient("qs-user-demo")
public interface UserClient {

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    User selectById(@PathVariable("id") Long id);
}
