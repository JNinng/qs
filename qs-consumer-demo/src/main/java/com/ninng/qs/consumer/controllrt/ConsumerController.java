package com.ninng.qs.consumer.controllrt;

import com.ninng.qs.consumer.domain.ConsumerResult;
import com.ninng.qs.consumer.service.ConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author OhmLaw
 * @Date 2022/9/15 17:29
 * @Version 1.0
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private final ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping("/{id}")
    public ConsumerResult selectById(@PathVariable("id") Long id) {
        return consumerService.selectById(id);
    }
}
