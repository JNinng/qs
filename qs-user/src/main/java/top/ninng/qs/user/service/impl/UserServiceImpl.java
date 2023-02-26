package top.ninng.qs.user.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Service;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.common.utils.EmptyCheck;
import top.ninng.qs.common.utils.IdObfuscator;
import top.ninng.qs.common.utils.Validator;
import top.ninng.qs.user.clients.ArticleClient;
import top.ninng.qs.user.config.IdConfig;
import top.ninng.qs.user.entity.ArticleData;
import top.ninng.qs.user.entity.LoginResult;
import top.ninng.qs.user.entity.User;
import top.ninng.qs.user.entity.UserInfo;
import top.ninng.qs.user.mapper.RelationMapper;
import top.ninng.qs.user.mapper.UserMapper;
import top.ninng.qs.user.service.IUserService;

import java.sql.Timestamp;

/**
 * @Author OhmLaw
 * @Date 2022/9/15 13:38
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements IUserService {

    //    private final IUserMapper userMapper;
    //    private final ITransactionalTest transactionalTest;
    //
    //    public UserServiceImpl(IUserMapper userMapper, ITransactionalTest transactionalTest) {
    //        this.userMapper = userMapper;
    //        this.transactionalTest = transactionalTest;
    //    }

    //    @GlobalTransactional
    //    public User selectById(Long id) {
    //        User user;
    //        user = userMapper.selectById(id);
    //        transactionalTest.update(id, LocalTime.now().getSecond());
    //        return user;
    //    }

    UserMapper userMapper;
    RelationMapper relationMapper;
    ArticleClient articleClient;
    IdObfuscator idObfuscator;

    public UserServiceImpl(UserMapper userMapper, RelationMapper relationMapper, ArticleClient articleClient,
                           IdObfuscator idObfuscator) {
        this.userMapper = userMapper;
        this.relationMapper = relationMapper;
        this.articleClient = articleClient;
        this.idObfuscator = idObfuscator;
    }

    /**
     * 检查登录状态
     *
     * @param id 目标用户 id
     * @return 登录状态
     */
    @Override
    public UnifyResponse<String> checkLogin(long id) {
        // TODO 登录状态逻辑
        // 根据 id 获取目标 token
        String tokenValueByLoginId = StpUtil.getTokenValueByLoginId(id);
        Object result = StpUtil.getLoginIdByToken(tokenValueByLoginId);
        System.out.println(result);
        if (EmptyCheck.notEmpty(result)) {
            long resultId = Long.parseLong((String) result);
            if (resultId == id) {
                return UnifyResponse.ok("已登录！");
            }
        }
        return UnifyResponse.ok(id + " 未登录！");
    }

    /**
     * 获取用户信息
     *
     * @param id 用户 id
     * @return 用户信息
     */
    @Override
    public UnifyResponse<UserInfo> getUserInfo(long id) {
        UserInfo userInfo;
        User user = userMapper.selectByPrimaryKey(id);
        if (EmptyCheck.notEmpty(user)) {
            userInfo = new UserInfo();
            userInfo.setId(idObfuscator.encode(user.getId(), IdConfig.USER_ID));
            userInfo.setName(user.getUserName());
            userInfo.setNickname(user.getNickname());
            userInfo.setEmail(user.getEmail());
            userInfo.setInfo(user.getInfo());
            userInfo.setCreateTime(user.getCreateTime());
            userInfo.setHeadPortrait(user.getHeadPortrait());
            userInfo.setSite("");
            userInfo.setFollowNumber(relationMapper.selectFollowCount(user.getId()));
            userInfo.setFansNumber(relationMapper.selectFansCount(user.getId()));
            UnifyResponse<ArticleData> response = articleClient.getUserArticleData(id);
            if (EmptyCheck.notEmpty(response)) {
                ArticleData data = response.getData();
                if (EmptyCheck.notEmpty(data)) {
                    userInfo.setArticleNumber(data.getArticleNumber());
                    userInfo.setPageViewNumber(data.getPageViewNumber());
                    userInfo.setGetLikes(data.getGetLikes());
                } else {
                    userInfo.setArticleNumber(0);
                    userInfo.setPageViewNumber(0);
                    userInfo.setGetLikes(0);
                }
            }
            return UnifyResponse.ok(userInfo);
        }
        return UnifyResponse.fail("id 错误！", null);
    }

    /**
     * 用户登录
     *
     * @param name     名称
     * @param password 密码
     * @return 登录结果
     */
    @Override
    public UnifyResponse<LoginResult> login(String name, String password) {
        User selectUser = userMapper.selectByName(name);
        if (EmptyCheck.isEmpty(selectUser)) {
            return UnifyResponse.fail("账号不存在！", null);
        }
        if (password.equals(selectUser.getUserPassword())) {
            StpUtil.login(selectUser.getId());
            return UnifyResponse.ok("登录成功！", new LoginResult(idObfuscator.encode(selectUser.getId(), 0),
                    StpUtil.getTokenValue()));
        }
        return UnifyResponse.fail("账号或密码错误！", null);
    }

    /**
     * 登出
     *
     * @return 登出结果
     */
    @Override
    public UnifyResponse<String> logout() {
        StpUtil.logout();
        return UnifyResponse.ok("已登出！");
    }

    @Override
    public UnifyResponse<String> register(String name, String email, String password) {
        String result = "";
        if (EmptyCheck.isEmpty(name) || name.length() > 100) {
            return UnifyResponse.fail("用户名有误！", null);
        }
        if (EmptyCheck.isEmpty(email) || !Validator.isEmail(email)) {
            return UnifyResponse.fail("邮箱有误！", null);
        }
        if (EmptyCheck.isEmpty(password) || password.length() < 4) {
            return UnifyResponse.fail("密码过于简单！", null);
        }
        User user = userMapper.selectByName(name);
        if (EmptyCheck.notEmpty(user)) {
            return UnifyResponse.fail("当前用户名已存在！", null);
        }
        user = new User();
        user.setUserName(name);
        user.setEmail(email);
        user.setUserPassword(password);
        user.setHeadPortrait("空");
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int i = userMapper.insertSelective(user);
        if (i > 0) {
            return UnifyResponse.ok("注册成功！", null);
        }
        return UnifyResponse.fail("注册失败！", null);
    }

    @Override
    public UnifyResponse<String> update(long userId, String nickname, String email, String info, String headPortrait,
                                        String oldPassword, String password) {
        User user = new User();
        user.setId(Math.toIntExact(userId));
        user.setNickname(nickname);
        user.setEmail(email);
        user.setInfo(info);
        user.setHeadPortrait(headPortrait);
        if (EmptyCheck.notEmpty(password)) {
            User user1 = userMapper.selectByPrimaryKey(userId);
            if (EmptyCheck.notEmpty(user1)) {
                if (oldPassword.equals(user1.getUserPassword())) {
                    user.setUserPassword(password);
                } else {
                    return UnifyResponse.fail("原密码有误！", null);
                }
            }
        }
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i > 0) {
            return UnifyResponse.ok();
        }
        return UnifyResponse.fail("更新失败！", null);
    }
}
