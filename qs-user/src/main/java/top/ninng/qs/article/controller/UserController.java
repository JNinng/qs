package top.ninng.qs.article.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ninng.entity.UnifyResponse;
import top.ninng.qs.article.entity.LoginResult;
import top.ninng.qs.article.service.IUserService;
import top.ninng.utils.IdObfuscator;

/**
 * 用户控制器
 *
 * @Author OhmLaw
 * @Date 2022/12/31 16:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    public IUserService iUserService;
    public IdObfuscator idObfuscator;

    public UserController(IUserService iUserService, IdObfuscator idObfuscator) {
        this.iUserService = iUserService;
        this.idObfuscator = idObfuscator;
    }

    /**
     * 检查指定用户登录状态
     *
     * @param id 检查目标用户 id
     * @return 登录状态
     */
    @RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
    public UnifyResponse<String> checkLogin(
            @RequestParam(value = "id") String id) {
        if (StpUtil.isLogin()) {
            long[] realId = idObfuscator.decode(id, 0);
            if (realId.length > 0) {
                return iUserService.checkLogin(realId[0]);
            }
        }
        return UnifyResponse.fail("您还未登录！");
    }

    /**
     * 用户登录
     *
     * @param name     用户名
     * @param password 密码
     * @return 登录结果
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UnifyResponse<LoginResult> login(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "password") String password) {
        return iUserService.login(name, password);
    }

    /**
     * 登出
     *
     * @return 登出结果
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public UnifyResponse<String> logout() {
        return iUserService.logout();
    }
}
