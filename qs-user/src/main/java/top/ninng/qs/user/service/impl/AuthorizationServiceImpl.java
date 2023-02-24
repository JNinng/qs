package top.ninng.qs.user.service.impl;

import org.springframework.stereotype.Service;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.common.utils.EmptyCheck;
import top.ninng.qs.common.utils.IdObfuscator;
import top.ninng.qs.user.config.IdConfig;
import top.ninng.qs.user.entity.Authorization;
import top.ninng.qs.user.mapper.AuthorizationMapper;
import top.ninng.qs.user.service.IAuthorizationService;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @Author OhmLaw
 * @Date 2023/2/24 20:26
 * @Version 1.0
 */
@Service
public class AuthorizationServiceImpl implements IAuthorizationService {

    public AuthorizationMapper authorizationMapper;
    public IdObfuscator idObfuscator;

    public AuthorizationServiceImpl(AuthorizationMapper authorizationMapper, IdObfuscator idObfuscator) {
        this.authorizationMapper = authorizationMapper;
        this.idObfuscator = idObfuscator;
    }

    @Override
    public UnifyResponse<String> deleteAuthorization(long id, long userId) {
        Authorization authorization = authorizationMapper.selectByPrimaryKey(id);
        if (EmptyCheck.notEmpty(authorization)) {
            if (userId == authorization.getUserId()) {
                authorizationMapper.deleteByPrimaryKey(id);
                return UnifyResponse.ok();
            }
            return UnifyResponse.fail("id 错误，删除失败！", null);
        }
        return UnifyResponse.fail("删除失败！", null);
    }

    @Override
    public UnifyResponse<String> getAuthorization(long userId, String code) {
        Authorization authorization = authorizationMapper.selectByUserIdAndCode(userId, code);
        if (EmptyCheck.notEmpty(authorization)) {
            return UnifyResponse.ok("正确！", null);
        }
        return UnifyResponse.fail("错误！", null);
    }

    @Override
    public UnifyResponse<ArrayList<Authorization>> getAuthorizationList(long userId) {
        ArrayList<Authorization> authorizationArrayList = authorizationMapper.selectByUserId(userId);
        authorizationArrayList =
                authorizationArrayList.stream().peek(this::obfuscatorId).collect(Collectors.toCollection(ArrayList::new));
        return UnifyResponse.ok(authorizationArrayList);
    }

    @Override
    public UnifyResponse<String> setAuthorization(long userId, String code, String info) {
        Authorization authorization = new Authorization();
        authorization.setUserId(Math.toIntExact(userId));
        authorization.setCode(code);
        authorization.setInfo(info);
        int insert = authorizationMapper.insert(authorization);
        if (insert > 0) {
            return UnifyResponse.ok("添加成功！", null);
        }
        return UnifyResponse.fail("添加失败！", null);
    }

    private void obfuscatorId(Authorization authorization) {
        authorization.setObfuscatorId(idObfuscator.encode(authorization.getId(), IdConfig.AUTHORIZATION_ID));
    }
}
