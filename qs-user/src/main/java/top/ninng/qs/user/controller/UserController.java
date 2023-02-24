package top.ninng.qs.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.common.utils.EmptyCheck;
import top.ninng.qs.common.utils.IdObfuscator;
import top.ninng.qs.user.config.IdConfig;
import top.ninng.qs.user.entity.LoginResult;
import top.ninng.qs.user.entity.UserInfo;
import top.ninng.qs.user.service.IUserService;

import javax.servlet.http.HttpServletRequest;

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
     * 获取用户信息
     *
     * @param id 用户 id
     * @return 登录结果
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public UnifyResponse<UserInfo> getUserInfo(
            @RequestParam(value = "id") String id) {
        long[] realId = idObfuscator.decode(id, IdConfig.USER_ID);
        if (realId.length > 0) {
            return iUserService.getUserInfo(realId[0]);
        }
        return UnifyResponse.fail("id 错误", null);
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

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public UnifyResponse<String> update(
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "info", required = false) String info,
            @RequestParam(value = "headPortrait", required = false) String headPortrait,
            @RequestParam(value = "oldPassword", required = false) String oldPassword,
            @RequestParam(value = "password", required = false) String password,
            HttpServletRequest request) {
        if (EmptyCheck.notEmpty(password)) {
            if (password.length() < 6) {
                return UnifyResponse.fail("密码过于简单！", null);
            }
        }
        String loginId = request.getHeader("user_id");
        long userId = 0L;
        if (EmptyCheck.notEmpty(loginId)) {
            userId = Long.parseLong(loginId);
        } else {
            return UnifyResponse.fail("id 错误！", null);
        }
        return iUserService.update(userId, nickname, email, info, headPortrait, oldPassword, password);
    }
}
