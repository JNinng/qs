package top.ninng.qs.user.service;

import top.ninng.entity.UnifyResponse;
import top.ninng.qs.user.entity.ArticleIdListPageResult;
import top.ninng.qs.user.entity.Tag;

import java.util.ArrayList;

/**
 * 标签服务接口
 *
 * @Author OhmLaw
 * @Date 2023/1/13 20:02
 * @Version 1.0
 */
public interface ITagService {

    /**
     * 提交标签
     *
     * @param tagName 标签名
     * @return 添加结果
     */
    UnifyResponse<String> addTag(String tagName);

    /**
     * 根据标签名删除标签
     *
     * @param id 标签 id
     * @return 删除结果
     */
    UnifyResponse<String> deleteTagById(Long id);

    /**
     * 根据标签名删除标签
     *
     * @param tagName 标签名
     * @return 删除结果
     */
    UnifyResponse<String> deleteTagByName(String tagName);

    /**
     * 获取全部标签
     *
     * @return 全部标签
     */
    UnifyResponse<ArrayList<Tag>> getAllTag();

    /**
     * 根据标签名获取文章 id 列表
     *
     * @param tagName  标签名
     * @param page     页数
     * @param pageSize 页大小
     * @return 指定标签结果文章 id 列表
     */
    UnifyResponse<ArticleIdListPageResult> getArticleIdListPageByName(
            String tagName, int page, int pageSize);

    /**
     * 根据标签 id 获取标签信息
     *
     * @param id 标签 id
     * @return 标签
     */
    UnifyResponse<Tag> getTagById(long id);

    /**
     * 根据标签名更新标签
     *
     * @param id      标签 id
     * @param tagName 标签名
     * @return 更新结果
     */
    UnifyResponse<String> updateTagById(Long id, String tagName);
}
