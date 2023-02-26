package top.ninng.qs.user.service;

import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.user.entity.LoginResult;
import top.ninng.qs.user.entity.UserInfo;

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
     * 获取用户信息
     *
     * @param id 用户 id
     * @return 用户信息
     */
    UnifyResponse<UserInfo> getUserInfo(long id);

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

    /**
     * 注册
     *
     * @param name     名称
     * @param email    邮箱
     * @param password 密码
     * @return 注册结果
     */
    UnifyResponse<String> register(String name, String email, String password);

    /**
     * 更新用户信息
     *
     * @param userId   用户 id
     * @param nickname 昵称
     * @param email    邮箱
     * @param info     简介
     * @return 更新结果
     */
    UnifyResponse<String> update(long userId, String nickname, String email, String info, String headPortrait,
                                 String oldPassword, String password);
}
