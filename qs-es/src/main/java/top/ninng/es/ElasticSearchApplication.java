package top.ninng.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author OhmLaw
 * @Date 2022/9/25 12:20
 * @Version 1.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ElasticSearchApplication {

    public static void main(String[] args) {
        // 解决Nacos日志输出路径冲突
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(ElasticSearchApplication.class, args);
    }
}
