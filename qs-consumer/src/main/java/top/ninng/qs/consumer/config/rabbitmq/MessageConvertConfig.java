package top.ninng.qs.consumer.config.rabbitmq;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author OhmLaw
 * @Date 2022/10/1 17:37
 * @Version 1.0
 */
@Configuration
public class MessageConvertConfig {

    @Bean
    public MessageConverter getMessageConverter() {
        return new FastJsonMessageConverter();
    }
}
