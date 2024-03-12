package top.ninng.qs.admin.mapper;

import org.springframework.stereotype.Repository;
import top.ninng.qs.admin.entity.Config;

import java.util.ArrayList;

/**
 * @author OhmLaw
 * @description 针对表【config(配置信息)】的数据库操作Mapper
 * @createDate 2023-01-10 19:56:51
 * @Entity top.ninng.qs.common.entity.Config
 */
@Repository("configMapper")
public interface ConfigMapper {

    /**
     * 根据 id 删除配置
     *
     * @param id 配置 id
     * @return 删除结果
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入一条完整配置信息
     *
     * @param config 配置信息
     * @return 插入结果
     */
    int insert(Config config);

    /**
     * 选择性插入一条配置
     *
     * @param config 配置信息
     * @return 插入结果
     */
    int insertSelective(Config config);

    /**
     * 查询所有配置信息
     *
     * @return 所有配置信息
     */
    ArrayList<Config> selectAll();

    /**
     * 根据 id 查询配置信息
     *
     * @param id 配置 id
     * @return 配置信息
     */
    Config selectByPrimaryKey(Long id);

    /**
     * 根据 id 更新一条完整配置信息
     *
     * @param config 配置 id
     * @return 更新结果
     */
    int updateByPrimaryKey(Config config);

    /**
     * 根据 id 选择性更新一条配置信息
     *
     * @param config 配置 id
     * @return 更新结果
     */
    int updateByPrimaryKeySelective(Config config);

}
