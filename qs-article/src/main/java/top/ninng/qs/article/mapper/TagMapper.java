package top.ninng.qs.article.mapper;

import org.springframework.stereotype.Repository;
import top.ninng.qs.article.entity.Tag;

import java.util.ArrayList;

/**
 * @author OhmLaw
 * @description 针对表【tag(标签)】的数据库操作Mapper
 * @createDate 2023-01-13 19:18:52
 * @Entity top.ninng.qs.common.entity.Tag
 */
@Repository("tagMapper")
public interface TagMapper {

    /**
     * 根据标签名删除
     *
     * @param tagName 标签名
     * @return 删除结果
     */
    int deleteByName(String tagName);

    /**
     * 根据标签 id 删除
     *
     * @param id 标签 id
     * @return 删除结果
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入一条标签信息
     *
     * @param tag 标签
     * @return 插入结果
     */
    int insert(Tag tag);

    /**
     * 选择性插入一条标签信息
     *
     * @param record 标签
     * @return 插入结果
     */
    int insertSelective(Tag record);

    /**
     * 查询所有标签
     *
     * @return 所有标签
     */
    ArrayList<Tag> selectAll();

    /**
     * 根据 id 查询标签信息
     *
     * @param id 标签 id
     * @return 查询结果
     */
    Tag selectByPrimaryKey(Long id);

    /**
     * 根据标签名查询标签 id
     *
     * @param tagName 标签名
     * @return 标签 id
     */
    Long selectIdByName(String tagName);

    /**
     * 根据 id 更新标签信息
     *
     * @param record 标签 id
     * @return 更新结果
     */
    int updateByPrimaryKey(Tag record);

    /**
     * 根据 id 选择性更新标签信息
     *
     * @param record 标签
     * @return 更新结果
     */
    int updateByPrimaryKeySelective(Tag record);

}
