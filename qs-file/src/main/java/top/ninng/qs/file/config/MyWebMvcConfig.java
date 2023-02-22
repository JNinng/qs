package top.ninng.qs.file.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.ninng.qs.file.utils.Path;

import java.nio.charset.StandardCharsets;

/**
 * 配置 fastjson 为默认 json 解析器
 *
 * @Author OhmLaw
 * @Date 2023/1/6 18:21
 * @Version 1.0
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置静态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:///" + Path.getWorkAbsolutePath() + "/data/img/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //        registry.addMapping("/**")
        //                //是否发送Cookie
        //                .allowCredentials(true)
        //                //设置放行哪些原始域   SpringBoot2.4.4下低版本使用.allowedOrigins("*")
        //                .allowedOriginPatterns("*")
        //                //放行哪些请求方式
        //                .allowedMethods("GET", "POST", "PUT", "DELETE")
        //                //.allowedMethods("*") //或者放行全部
        //                //放行哪些原始请求头部信息
        //                .allowedHeaders("*")
        //                //暴露哪些原始请求头部信息
        //                .exposedHeaders("*");
    }

    /**
     * 配置 fastjson 为默认 json 解析器
     *
     * @return
     */
    @Bean
    FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd");
        config.setCharset(StandardCharsets.UTF_8);
        config.setSerializerFeatures(
                //输出类名
                //                SerializerFeature.WriteClassName,
                //输出map中value为null的数据
                SerializerFeature.WriteMapNullValue,
                //json格式化
                SerializerFeature.PrettyFormat,
                //输出空list为[]，而不是null
                SerializerFeature.WriteNullListAsEmpty,
                //输出空string为""，而不是null
                SerializerFeature.WriteNullStringAsEmpty
        );
        converter.setFastJsonConfig(config);
        return converter;
    }
}
