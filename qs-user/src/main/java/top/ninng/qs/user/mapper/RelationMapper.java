package top.ninng.qs.user.mapper;

import org.springframework.stereotype.Repository;
import top.ninng.qs.user.entity.Relation;

import java.util.ArrayList;

/**
 * @author OhmLaw
 * @description 针对表【relation(用户关系表，a关注（或其他）b的关系)】的数据库操作Mapper
 * @createDate 2023-02-24 22:06:15
 * @Entity top.ninng.qs.user.entity.Relation
 */
@Repository("relationMapper")
public interface RelationMapper {

    /**
     * 删除关系
     *
     * @param aUserId 用户 a
     * @param bUserId 用户 b
     * @return 删除结果
     */
    int cancelFollow(Long aUserId, Long bUserId);

    /**
     * 根据 id 删除关系
     *
     * @param id
     * @return 删除结果
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入新关系
     *
     * @param relation
     * @return 插入结果
     */
    int insert(Relation relation);

    /**
     * 选择性插入
     *
     * @param relation
     * @return 插入结果
     */
    int insertSelective(Relation relation);

    /**
     * 根据 id 查询关系
     *
     * @param id 关系 id
     * @return 关系
     */
    Relation selectByPrimaryKey(Long id);

    /**
     * 获取用户粉丝列表
     *
     * @param bUserId 用户 id
     * @param l       左边界
     * @param r       有边界
     * @return 关注列表
     */
    ArrayList<Long> selectFansByPage(long bUserId, int l, int r);

    /**
     * 获取用户粉丝数量
     *
     * @param bUserId 用户 id
     * @return 分数数量
     */
    int selectFansCount(long bUserId);

    /**
     * 获取用户关注列表
     *
     * @param aUserId 用户 id
     * @param l       左边界
     * @param r       有边界
     * @return 关注列表
     */
    ArrayList<Long> selectFollowByPage(long aUserId, int l, int r);

    /**
     * 获取用户关注数量
     *
     * @param aUserId 用户 id
     * @return 关注数量
     */
    int selectFollowCount(long aUserId);

    /**
     * 更新关系
     *
     * @param relation 关系
     * @return 更新结果
     */
    int updateByPrimaryKey(Relation relation);

    /**
     * 选择性更新
     *
     * @param relation 关系
     * @return 更新结果
     */
    int updateByPrimaryKeySelective(Relation relation);
}
