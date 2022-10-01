package com.ninng.qs.consumer.controllrt;

import com.ninng.qs.consumer.domain.ConsumerResult;
import com.ninng.qs.consumer.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author OhmLaw
 * @Date 2022/9/15 17:29
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private final ConsumerService consumerService;
    private final RabbitTemplate rabbitTemplate;

    public ConsumerController(ConsumerService consumerService, RabbitTemplate rabbitTemplate) {
        this.consumerService = consumerService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/get/{id}")
    public ConsumerResult selectById(@PathVariable("id") Long id) {
        return consumerService.selectById(id);
    }

    @GetMapping("/send/{message}")
    public String sendMessage(@PathVariable("message") String message) {
        String queueName = "demo.queue";
        rabbitTemplate.convertAndSend(queueName, message);
        return "OK";
    }

    @GetMapping("/send2DE/{message}")
    public String sendMessage2DirectExchange(@PathVariable("message") String message) {
        String exchangeName = "demo.direct0";
        String key = LocalTime.now().getSecond() % 2 == 1 ? "one" : "two";
        log.info("key: " + key);
        rabbitTemplate.convertAndSend(exchangeName, key, message);
        return "direct0 exchange ok";
    }

    @GetMapping("/send2FE/{message}")
    public String sendMessage2FanoutExchange(@PathVariable("message") String message) {
        String exchangeName = "demo.fanout0";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
        return "fanout exchange ok";
    }

    @GetMapping("/send2TE/{message}")
    public String sendMessage2TopicExchange(@PathVariable String message) {
        String exchangeName = "demo.topic0";
        String key = LocalTime.now().getSecond() % 2 == 1 ? "one" : "two";
        log.info("key: " + key);
        rabbitTemplate.convertAndSend(exchangeName, key + ".all", message);
        return "topic exchange ok";
    }

    @GetMapping("/sendO2FanoutExchange/{message}")
    public String sendObject2FanoutExchange(@PathVariable String message) {
        String exchangeName = "demo.fanout0";
        Map<String, String> msg = new HashMap<>(1);
        msg.put("message", message);
        //        rabbitTemplate.setMessageConverter(new FastJsonMessageConverter());
        rabbitTemplate.convertAndSend(exchangeName, "", msg);
        return "object to fanout exchange ok";
    }
}
