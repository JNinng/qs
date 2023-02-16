package top.ninng.qs.user;

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
@MapperScan("top.ninng.qs.article.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class ArticleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args);
    }
}
