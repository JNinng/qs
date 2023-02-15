package top.ninng.qs.consumer.config.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * MessageConverter适配Fastjson
 *
 * @Author OhmLaw
 * @Date 2022/10/1 17:39
 * @Version 1.0
 */
public class FastJsonMessageConverter extends AbstractMessageConverter {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final Map<String, Function<Message, Object>> MSG_CONVERTER_MAP = new HashMap<>();

    static {
        MSG_CONVERTER_MAP.put(MessageProperties.CONTENT_TYPE_BYTES, message -> message.getBody());
        MSG_CONVERTER_MAP.put(MessageProperties.CONTENT_TYPE_TEXT_PLAIN, message -> {
            try {
                return new String(message.getBody(), DEFAULT_CHARSET);
            } catch (UnsupportedEncodingException e) {
                throw new MessageConversionException("Failed to convert Message content", e);
            }
        });
        MSG_CONVERTER_MAP.put(MessageProperties.CONTENT_TYPE_JSON, message -> {
            try {
                return JSON.parse(new String(message.getBody(), DEFAULT_CHARSET));
            } catch (UnsupportedEncodingException e) {
                throw new MessageConversionException("Failed to convert Message content", e);
            }
        });
    }

    public FastJsonMessageConverter() {
        super();
    }

    @Override
    protected Message createMessage(Object o, MessageProperties messageProperties) {
        byte[] bytes = null;
        try {
            if (o instanceof byte[]) {
                messageProperties.setContentType(MessageProperties.CONTENT_TYPE_BYTES);
                bytes = (byte[]) o;
            } else if (o instanceof CharSequence) {
                messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
                messageProperties.setContentEncoding(DEFAULT_CHARSET);
                bytes = o.toString().getBytes(DEFAULT_CHARSET);
            } else {
                messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
                String jsonString = JSON.toJSONString(o, SerializerFeature.WriteClassName);
                bytes = jsonString.getBytes(DEFAULT_CHARSET);
            }
        } catch (UnsupportedEncodingException e) {
            throw new MessageConversionException("Failed to convert Message content", e);
        }
        if (bytes != null) {
            messageProperties.setContentLength(bytes.length);
        }
        return new Message(bytes, messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        MessageProperties messageProperties = message.getMessageProperties();
        return MSG_CONVERTER_MAP.get(messageProperties.getContentType()).apply(message);
    }
}
