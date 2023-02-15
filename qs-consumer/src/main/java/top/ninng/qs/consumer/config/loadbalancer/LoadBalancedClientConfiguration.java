package top.ninng.qs.consumer.config.loadbalancer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * LoadBalanced负载均衡客户端配置
 *
 * @Author OhmLaw
 * @Date 2022/9/17 0:29
 * @Version 1.0
 */
//@Configuration
//@LoadBalancerClient(value = "userService", configuration = CustomLoadBalancerConfiguration.class)
public class LoadBalancedClientConfiguration {

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }
}
