package top.ninng.qs.user.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.user.entity.ArticleData;

/**
 * @Author OhmLaw
 * @Date 2023/2/19 11:27
 * @Version 1.0
 */
@FeignClient("qs-article")
public interface ArticleClient {

    /**
     * 获取用户文章数据：文章总数、被收藏数、总访问数量
     *
     * @param id 用户 id
     * @return 数据信息
     */
    @PostMapping("/article/getUserArticleData")
    UnifyResponse<ArticleData> getUserArticleData(
            @RequestParam("id") long id);
}
