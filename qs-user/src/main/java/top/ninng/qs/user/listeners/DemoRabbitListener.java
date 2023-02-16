package top.ninng.qs.user.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

/**
 * 配置Rabbit监听，接收消息
 * 支持模型：WorkQueue,FanoutQueue,DirectQueue,TopicQueue
 *
 * @Author OhmLaw
 * @Date 2022/9/30 23:50
 * @Version 1.0
 */
@Slf4j
@Component
public class DemoRabbitListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "demo.direct0.queue0"),
            exchange = @Exchange(name = "demo.direct0", type = ExchangeTypes.DIRECT),
            key = {"one", "two"}
    ))
    public void listenerDirectQueue0(String message) throws InterruptedException {
        Thread.sleep(20);
        log.info("demo.direct0.queue0 listenerDirectQueue0: " + message + LocalTime.now().toString());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "demo.direct0.queue1"),
            exchange = @Exchange(name = "demo.direct0", type = ExchangeTypes.DIRECT),
            key = {"two"}
    ))
    public void listenerDirectQueue1(String message) throws InterruptedException {
        Thread.sleep(20);
        log.info("demo.direct0.queue1 listenerDirectQueue1: " + message + LocalTime.now().toString());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "demo.fanout0.queue0"),
            exchange = @Exchange(name = "demo.fanout0", type = ExchangeTypes.FANOUT)
    ))
    public void listenerFanoutExchange0(Map<String, String> message) throws InterruptedException {
        Thread.sleep(200);
        log.info("demo.fanout0.queue0 listenerFanoutExchange0:" + message + LocalTime.now().toString());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "demo.fanout0.queue1"),
            exchange = @Exchange(name = "demo.fanout0", type = ExchangeTypes.FANOUT)
    ))
    public void listenerFanoutExchange1(Map<String, String> message) throws InterruptedException {
        Thread.sleep(200);
        log.info("demo.fanout0.queue1 listenerFanoutExchange1:" + message.get("message") + LocalTime.now().toString());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "demo.topic0.queue0"),
            exchange = @Exchange(name = "demo.topic0", type = ExchangeTypes.TOPIC),
            key = "one.#"
    ))
    public void listenerTopicExchange0(String message) throws InterruptedException {
        Thread.sleep(20);
        log.info("demo.topic0.queue0 one.# listenerTopicExchange0:" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "demo.topic0.queue1"),
            exchange = @Exchange(name = "demo.topic0", type = ExchangeTypes.TOPIC),
            key = "two.#"
    ))
    public void listenerTopicExchange1(String message) throws InterruptedException {
        Thread.sleep(200);
        log.info("demo.topic0.queue1 two.# listenerTopicExchange1:" + message);
    }

    @RabbitListener(queues = "demo.queue")
    public void listenerWorkQueue0(String message) throws InterruptedException {
        Thread.sleep(20);
        log.info("demo.queue listenerWorkQueue0: " + message + LocalTime.now().toString());
    }

    @RabbitListener(queues = "demo.queue")
    public void listenerWorkQueue1(String message) throws InterruptedException {
        Thread.sleep(200);
        log.error("demo.queue listenerWorkQueue1: " + message + LocalTime.now().toString());
    }
}
