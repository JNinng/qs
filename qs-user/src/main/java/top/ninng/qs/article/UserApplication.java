package top.ninng.qs.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author OhmLaw
 * @Date 2022/9/12 18:48
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("top.ninng.qs.user.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
