package top.ninng.qs.user.service.impl;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.common.utils.EmptyCheck;
import top.ninng.qs.common.utils.IdObfuscator;
import top.ninng.qs.user.config.IdConfig;
import top.ninng.qs.user.entity.Relation;
import top.ninng.qs.user.entity.RelationInfo;
import top.ninng.qs.user.entity.RelationItem;
import top.ninng.qs.user.entity.User;
import top.ninng.qs.user.mapper.RelationMapper;
import top.ninng.qs.user.mapper.UserMapper;
import top.ninng.qs.user.service.IRelationService;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @Author OhmLaw
 * @Date 2023/2/24 22:40
 * @Version 1.0
 */
@Service
public class RelationServiceImpl implements IRelationService {

    public RelationMapper relationMapper;
    public UserMapper userMapper;
    public IdObfuscator idObfuscator;

    public RelationServiceImpl(RelationMapper relationMapper, UserMapper userMapper, IdObfuscator idObfuscator) {
        this.relationMapper = relationMapper;
        this.userMapper = userMapper;
        this.idObfuscator = idObfuscator;
    }

    @Override
    public UnifyResponse<String> cancelFollow(long aUserId, long bUserId) {
        int result = relationMapper.cancelFollow(aUserId, bUserId);
        if (result > 0) {
            return UnifyResponse.ok("取消关注成功！", null);
        }
        return UnifyResponse.ok("取消关注失败！", null);
    }

    @Override
    public UnifyResponse<RelationInfo> getFansByPage(long userId, int page, int pageSize) {
        // 处理网页分页逻辑和数据库分页查询逻辑
        page = (page <= 0) ? 1 : page;
        pageSize = (pageSize <= 0) ? 10 : pageSize;
        int sum = relationMapper.selectFansCount(userId);
        ArrayList<Long> longArrayList = relationMapper.selectFansByPage(userId, (page - 1) * pageSize, pageSize);
        return UnifyResponse.ok(getRelationInfo(userId, page, pageSize, sum, longArrayList));
    }

    @Override
    public UnifyResponse<RelationInfo> getFollowByPage(long userId, int page, int pageSize) {
        // 处理网页分页逻辑和数据库分页查询逻辑
        page = (page <= 0) ? 1 : page;
        pageSize = (pageSize <= 0) ? 10 : pageSize;
        int sum = relationMapper.selectFansCount(userId);
        ArrayList<Long> longArrayList = relationMapper.selectFollowByPage(userId, (page - 1) * pageSize, pageSize);
        return UnifyResponse.ok(getRelationInfo(userId, page, pageSize, sum, longArrayList));
    }

    @Override
    public UnifyResponse<String> setFollow(long aUserId, long bUserId) {
        Relation relation = new Relation();
        relation.setaUserId(Math.toIntExact(aUserId));
        relation.setbUserId(Math.toIntExact(bUserId));
        relationMapper.cancelFollow(aUserId, bUserId);
        int insert = relationMapper.insert(relation);
        if (insert > 0) {
            return UnifyResponse.ok("关注成功！", null);
        }
        return UnifyResponse.fail("关注失败！", null);
    }

    @NotNull
    private RelationInfo getRelationInfo(long userId, int page, int pageSize, int sum, ArrayList<Long> longArrayList) {
        ArrayList<RelationItem> collect = longArrayList.stream().map(aLong -> {
            User user = userMapper.selectByPrimaryKey(aLong);
            if (EmptyCheck.notEmpty(user)) {
                return new RelationItem(
                        idObfuscator.encode(user.getId(), IdConfig.USER_ID),
                        user.getNickname(),
                        user.getHeadPortrait());
            }
            return new RelationItem("", "账号已注销", "");
        }).collect(Collectors.toCollection(ArrayList::new));
        return new RelationInfo(sum, idObfuscator.encode(Math.toIntExact(userId), IdConfig.USER_ID), collect, page,
                pageSize);
    }
}
