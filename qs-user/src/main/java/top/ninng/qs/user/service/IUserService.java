package top.ninng.qs.user.service;

import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.user.entity.LoginResult;

/**
 * 用户服务接口
 *
 * @Author OhmLaw
 * @Date 2023/1/2 16:28
 * @Version 1.0
 */
public interface IUserService {

    /**
     * 用户是否登录
     *
     * @param id 目标用户 id
     * @return 登录状态
     */
    UnifyResponse<String> checkLogin(long id);

    /**
     * 用户登录
     *
     * @param name     名称
     * @param password 密码
     * @return 登录结果
     */
    UnifyResponse<LoginResult> login(String name, String password);

    /**
     * 用户登出
     *
     * @return 登出结果
     */
    UnifyResponse<String> logout();
}
