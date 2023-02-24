package top.ninng.qs.user.mapper;

import org.springframework.stereotype.Repository;
import top.ninng.qs.user.entity.Authorization;

import java.util.ArrayList;

/**
 * @author OhmLaw
 * @description 针对表【authorizsation】的数据库操作Mapper
 * @createDate 2023-02-24 17:20:51
 * @Entity top.ninng.qs.user.entity.Authorizsation
 */
@Repository("authorizationMapper")
public interface AuthorizationMapper {

    /**
     * 根据 id 删除授权码
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入授权码
     *
     * @param authorization
     * @return
     */
    int insert(Authorization authorization);

    /**
     * 选择性插入
     *
     * @param authorization
     * @return
     */
    int insertSelective(Authorization authorization);

    /**
     * 根据 id 查找授权码
     *
     * @param id
     * @return
     */
    Authorization selectByPrimaryKey(Long id);

    /**
     * 根据用户 id 查找授权码
     *
     * @param userId
     * @return
     */
    ArrayList<Authorization> selectByUserId(long userId);

    /**
     * 根据用户 id 和授权码查询
     *
     * @param userId 用户 id
     * @param code   授权码
     * @return 授权码
     */
    Authorization selectByUserIdAndCode(long userId, String code);

    /**
     * 根据 id 更新
     *
     * @param authorization
     * @return
     */
    int updateByPrimaryKey(Authorization authorization);

    /**
     * 选择性更新
     *
     * @param authorization
     * @return
     */
    int updateByPrimaryKeySelective(Authorization authorization);

}
