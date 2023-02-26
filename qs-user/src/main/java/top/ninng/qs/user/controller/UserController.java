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
import top.ninng.qs.user.entity.*;
import top.ninng.qs.user.service.IAuthorizationService;
import top.ninng.qs.user.service.IRelationService;
import top.ninng.qs.user.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

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
    public IAuthorizationService iAuthorizationService;
    public IRelationService iRelationService;
    public IdObfuscator idObfuscator;

    public UserController(IUserService iUserService, IAuthorizationService iAuthorizationService,
                          IRelationService iRelationService, IdObfuscator idObfuscator) {
        this.iUserService = iUserService;
        this.iAuthorizationService = iAuthorizationService;
        this.iRelationService = iRelationService;
        this.idObfuscator = idObfuscator;
    }

    @RequestMapping(value = "/cancelFollow", method = RequestMethod.POST)
    public UnifyResponse<String> cancelFollow(
            @RequestParam(value = "bUserId") String bUserIdStr,
            HttpServletRequest request) {
        String aUserIdStr = request.getHeader("user_id");
        if (EmptyCheck.isEmpty(aUserIdStr)) {
            return UnifyResponse.fail("id 错误！", null);
        }
        long aUserId = Long.parseLong(aUserIdStr);
        long[] bUserId = idObfuscator.decode(bUserIdStr, IdConfig.USER_ID);
        if (bUserId.length > 0) {
            return iRelationService.cancelFollow(aUserId, bUserId[0]);
        }
        return UnifyResponse.fail("id 错误！", null);
    }

    @RequestMapping(value = "/checkAuthorization", method = RequestMethod.POST)
    public UnifyResponse<String> checkAuthentication(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "code") String code) {
        long[] realUserId = new long[]{0L};
        if (EmptyCheck.notEmpty(userId)) {
            realUserId = idObfuscator.decode(userId, IdConfig.USER_ID);
        } else {
            return UnifyResponse.fail("错误！", null);
        }
        return iAuthorizationService.getAuthorization(realUserId[0], code);
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

    @RequestMapping(value = "/deleteAuthorization", method = RequestMethod.POST)
    public UnifyResponse<String> deleteAuthentication(
            @RequestParam(value = "id") String id,
            HttpServletRequest request) {
        String loginId = request.getHeader("user_id");
        long userId = 0L;
        long[] realId = idObfuscator.decode(id, IdConfig.AUTHORIZATION_ID);
        if (EmptyCheck.isEmpty(loginId) || realId.length == 0) {
            return UnifyResponse.fail("id 错误！", null);
        }
        userId = Long.parseLong(loginId);
        return iAuthorizationService.deleteAuthorization(realId[0], userId);
    }

    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    public UnifyResponse<String> follow(
            @RequestParam(value = "bUserId") String bUserIdStr,
            HttpServletRequest request) {
        String aUserIdStr = request.getHeader("user_id");
        if (EmptyCheck.isEmpty(aUserIdStr)) {
            return UnifyResponse.fail("id 错误！", null);
        }
        long aUserId = Long.parseLong(aUserIdStr);
        long[] bUserId = idObfuscator.decode(bUserIdStr, IdConfig.USER_ID);
        if (bUserId.length > 0) {
            return iRelationService.setFollow(aUserId, bUserId[0]);
        }
        return UnifyResponse.fail("id 错误！", null);
    }

    @RequestMapping(value = "/getAuthorization", method = RequestMethod.POST)
    public UnifyResponse<ArrayList<Authorization>> getAuthentication(HttpServletRequest request) {
        String loginId = request.getHeader("user_id");
        long userId = 0L;
        if (EmptyCheck.notEmpty(loginId)) {
            userId = Long.parseLong(loginId);
        } else {
            return UnifyResponse.fail("id 错误！", null);
        }
        return iAuthorizationService.getAuthorizationList(userId);
    }

    @RequestMapping(value = "/getFans", method = RequestMethod.POST)
    public UnifyResponse<RelationInfo> getFans(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize) {
        long[] userId = idObfuscator.decode(id, IdConfig.USER_ID);
        if (userId.length > 0) {
            return iRelationService.getFansByPage(userId[0], page, pageSize);
        }
        return UnifyResponse.fail("id 错误！", null);
    }

    @RequestMapping(value = "/getFollow", method = RequestMethod.POST)
    public UnifyResponse<RelationInfo> getFollow(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize) {
        long[] userId = idObfuscator.decode(id, IdConfig.USER_ID);
        if (userId.length > 0) {
            return iRelationService.getFollowByPage(userId[0], page, pageSize);
        }
        return UnifyResponse.fail("id 错误！", null);
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

    /**
     * 根据 id 查询用户
     * 内部接口
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectById", method = RequestMethod.POST)
    public UnifyResponse<User> selectUserById(@RequestParam(value = "id") Long id) {
        return iUserService.selectUserById(id);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UnifyResponse<String> register(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password) {
        return iUserService.register(name, email, password);
    }

    @RequestMapping(value = "/setAuthorization", method = RequestMethod.POST)
    public UnifyResponse<String> setAuthentication(
            @RequestParam(value = "code") String code,
            @RequestParam(value = "info") String info,
            HttpServletRequest request) {
        String loginId = request.getHeader("user_id");
        long userId = 0L;
        if (EmptyCheck.notEmpty(loginId)) {
            userId = Long.parseLong(loginId);
        } else {
            return UnifyResponse.fail("id 错误！", null);
        }
        return iAuthorizationService.setAuthorization(userId, code, info);
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
