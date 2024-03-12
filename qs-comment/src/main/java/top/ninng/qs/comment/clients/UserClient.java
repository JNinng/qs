package top.ninng.qs.comment.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import top.ninng.qs.comment.entity.User;
import top.ninng.qs.common.entity.UnifyResponse;

/**
 * @Author OhmLaw
 * @Date 2022/9/15 17:08
 * @Version 1.0
 */
@FeignClient("qs-user")
public interface UserClient {

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/selectById", method = RequestMethod.POST)
    UnifyResponse<User> selectById(@RequestParam("id") Long id);
}
