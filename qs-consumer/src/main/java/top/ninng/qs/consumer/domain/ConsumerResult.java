package top.ninng.qs.consumer.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author OhmLaw
 * @Date 2022/9/19 11:51
 * @Version 1.0
 */
@Data
public class ConsumerResult implements Serializable {

    Consumer consumer;
    User user;

    public ConsumerResult(Consumer consumer, User user) {
        this.consumer = consumer;
        this.user = user;
    }
}
