package top.ninng.qs.article.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @Author OhmLaw
 * @Date 2023/2/21 11:22
 * @Version 1.0
 */
@FeignClient("qs-es")
public interface EsClient {

    @RequestMapping(value = "/es/index/saveArticle", method = RequestMethod.POST, consumes =
            MediaType.APPLICATION_JSON_VALUE)
    void saveArticle(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "title") String title,
            @RequestBody String content,
            @RequestParam(value = "createTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createTime,
            @RequestParam(value = "updateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date updateTime
    );
}
