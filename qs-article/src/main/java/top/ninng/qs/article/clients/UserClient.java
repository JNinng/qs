package top.ninng.qs.article.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import top.ninng.qs.common.entity.UnifyResponse;

/**
 * @Author OhmLaw
 * @Date 2022/9/15 17:08
 * @Version 1.0
 */
@FeignClient("qs-user")
public interface UserClient {

    /**
     * 根据 id 查询授权
     *
     * @param id   用户 id
     * @param code 授权码
     * @return
     */
    @RequestMapping(value = "/user/checkAuthorization", method = RequestMethod.POST)
    UnifyResponse<String> checkAuthorization(@RequestParam("userId") String id, @RequestParam("code") String code);
}
