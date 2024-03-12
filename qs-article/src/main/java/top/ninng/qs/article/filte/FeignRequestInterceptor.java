package top.ninng.qs.article.filte;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * @Author OhmLaw
 * @Date 2023/2/19 12:20
 * @Version 1.0
 */
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("gateway_token", "6ff71f1a-68ec-4f76-b0c3-e39882e574a0");
    }
}
