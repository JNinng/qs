package top.ninng.qs.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.ninng.qs.common.utils.IdObfuscator;

/**
 * @Author OhmLaw
 * @Date 2023/1/2 17:27
 * @Version 1.0
 */
@Configuration
public class IdConfig {

    /**
     * user id 混淆模式索引
     */
    public static int USER_ID = 0;
    /**
     * article id 混淆模式索引
     */
    public static int ARTICLE_ID = 1;
    /**
     * comment id 混淆模式索引
     */
    public static int COMMENT_ID = 2;
    /**
     * tag id 混淆模式索引
     */
    public static int TAG_ID = 3;

    /**
     * 获取混淆器
     *
     * @return id 混淆器
     */
    @Bean
    public IdObfuscator getIdObfuscator() {
        return new IdObfuscator().init(
                new String[]{"dev userId key", "dev articleId key", "dev commentId key", "dev tagId key"},
                new int[]{8, 12, 12, 8});
    }
}
