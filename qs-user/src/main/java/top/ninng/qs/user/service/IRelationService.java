package top.ninng.qs.user.service;

import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.user.entity.RelationInfo;

/**
 * @Author OhmLaw
 * @Date 2023/2/24 22:28
 * @Version 1.0
 */
public interface IRelationService {

    /**
     * a 取消关注 b
     *
     * @param aUserId 用户 a
     * @param bUserId 用户 b
     * @return 取消关注结果
     */
    UnifyResponse<String> cancelFollow(long aUserId, long bUserId);

    /**
     * 分页查询用户粉丝列表
     *
     * @param userId   用户 id
     * @param page     页数
     * @param pageSize 页大小
     * @return 分页信息
     */
    UnifyResponse<RelationInfo> getFansByPage(long userId, int page, int pageSize);

    /**
     * 分页查询关注用户列表
     *
     * @param userId   用户 id
     * @param page     页数
     * @param pageSize 页大小
     * @return 分页信息
     */
    UnifyResponse<RelationInfo> getFollowByPage(long userId, int page, int pageSize);

    /**
     * a 关注 b
     *
     * @param aUserId 用户 a
     * @param bUserId 用户 b
     * @return 关注结果
     */
    UnifyResponse<String> setFollow(long aUserId, long bUserId);
}
