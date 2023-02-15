package top.ninng.qs.consumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import top.ninng.qs.consumer.config.loadbalancer.UserServiceLoadBalancerConfiguration;

/**
 * @Author OhmLaw
 * @Date 2022/9/15 17:21
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("top.ninng.qs.consumer.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@LoadBalancerClients(value = {
        @LoadBalancerClient(name = "qs-user", configuration = UserServiceLoadBalancerConfiguration.class)
})
public class ConsumerApplication {

    public static void main(String[] args) {
        // 解决Nacos日志输出路径冲突
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
