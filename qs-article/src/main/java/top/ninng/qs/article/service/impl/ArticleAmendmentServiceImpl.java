package top.ninng.qs.article.service.impl;

import org.springframework.stereotype.Service;
import top.ninng.qs.article.clients.EsClient;
import top.ninng.qs.article.entity.ArticleAmendment;
import top.ninng.qs.article.entity.ArticleAmendmentInfo;
import top.ninng.qs.article.mapper.ArticleAmendmentMapper;
import top.ninng.qs.article.service.IArticleAmendmentService;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.common.utils.IdObfuscator;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @Author OhmLaw
 * @Date 2023/3/3 15:29
 * @Version 1.0
 */
@Service
public class ArticleAmendmentServiceImpl implements IArticleAmendmentService {

    ArticleAmendmentMapper articleAmendmentMapper;
    EsClient esClient;
    IdObfuscator idObfuscator;

    public ArticleAmendmentServiceImpl(ArticleAmendmentMapper articleAmendmentMapper, EsClient esClient,
                                       IdObfuscator idObfuscator) {
        this.articleAmendmentMapper = articleAmendmentMapper;
        this.esClient = esClient;
        this.idObfuscator = idObfuscator;
    }

    @Override
    public UnifyResponse<String> amendment(long id, String articleId, int mode, int value, String info) {
        esClient.updateArticleCount(articleId, value);
        ArticleAmendment amendment = articleAmendmentMapper.selectByPrimaryKey(id);
        amendment.setType(mode);
        amendment.setInfo(amendment.getInfo() + "\n" + articleId + ">>" + mode + ":" + value);
        articleAmendmentMapper.updateByPrimaryKeySelective(amendment);
        return UnifyResponse.ok("已更新！", null);
    }

    @Override
    public UnifyResponse<ArticleAmendmentInfo> getArticleAmendmentInfo(int page, int pageSize) {
        // 分页逻辑处理
        page = (page < 0) ? 0 : page;
        pageSize = (pageSize <= 0) ? 10 : pageSize;
        int sum = articleAmendmentMapper.selectCount();
        ArrayList<ArticleAmendment> list = articleAmendmentMapper.getArticleAmendmentByPage((page - 1) * pageSize,
                pageSize);
        return UnifyResponse.ok(new ArticleAmendmentInfo(page, pageSize, sum, list));
    }

    @Override
    public UnifyResponse<String> submitAmendmentSuggest(String[] articleId, String info) {
        ArticleAmendment amendment = new ArticleAmendment();
        String id = articleId[0];
        for (int i = 1; i < articleId.length; i++) {
            id += "," + articleId[i];
        }
        amendment.setArticleId(id);
        amendment.setInfo(info);
        amendment.setCreateTime(new Timestamp(System.currentTimeMillis()));
        amendment.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        amendment.setDeleteStatus(false);
        amendment.setType(0);
        int insert = articleAmendmentMapper.insert(amendment);
        if (insert > 0) {
            return UnifyResponse.ok("提交成功！", null);
        }
        return UnifyResponse.fail("提交失败！", null);
    }
}
