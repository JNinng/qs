package top.ninng.qs.article.service;

import top.ninng.qs.article.entity.ArticleAmendmentInfo;
import top.ninng.qs.common.entity.UnifyResponse;

/**
 * @Author OhmLaw
 * @Date 2023/3/3 15:22
 * @Version 1.0
 */
public interface IArticleAmendmentService {

    /**
     * 资源修正
     *
     * @param articleId
     * @param mode
     * @param value
     * @return
     */
    UnifyResponse<String> amendment(long id, String articleId, int mode, int value, String info);

    /**
     * 分页获取资源修正信息
     *
     * @param page
     * @param pageSize
     * @return
     */
    UnifyResponse<ArticleAmendmentInfo> getArticleAmendmentInfo(int page, int pageSize);

    /**
     * 提交修正提议
     *
     * @param articleId
     * @param info
     * @return
     */
    UnifyResponse<String> submitAmendmentSuggest(String[] articleId, String info);
}
