package top.ninng.qs.user.service;

import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.user.entity.Authorization;

import java.util.ArrayList;

/**
 * @Author OhmLaw
 * @Date 2023/2/24 20:23
 * @Version 1.0
 */
public interface IAuthorizationService {

    /**
     * 删除授权码
     *
     * @param id     授权码 id
     * @param userId 用户 id
     * @return 保存结果
     */
    UnifyResponse<String> deleteAuthorization(long id, long userId);

    /**
     * 根据用户 id 判断授权码是否正确
     *
     * @param userId 用户 id
     * @param code   授权码
     * @return 结果
     */
    UnifyResponse<String> getAuthorization(long userId, String code);

    /**
     * 获取用户授权码
     *
     * @param userId 用户 id
     * @return
     */
    UnifyResponse<ArrayList<Authorization>> getAuthorizationList(long userId);

    /**
     * 设置授权码
     *
     * @param userId 用户 id
     * @param code   授权码
     * @param info   备注
     * @return 保存结果
     */
    UnifyResponse<String> setAuthorization(long userId, String code, String info);
}
