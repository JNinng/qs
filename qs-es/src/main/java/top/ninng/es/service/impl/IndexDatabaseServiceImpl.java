package top.ninng.es.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import top.ninng.es.entity.ArticleDocument;
import top.ninng.es.entity.User;
import top.ninng.es.repository.UserRepository;
import top.ninng.es.service.IndexDatabaseService;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.common.utils.EmptyCheck;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author OhmLaw
 * @Date 2022/9/25 16:53
 * @Version 1.0
 */
@Slf4j
@Service
public class IndexDatabaseServiceImpl implements IndexDatabaseService {

    private final UserRepository userRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public IndexDatabaseServiceImpl(UserRepository userRepository,
                                    ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.userRepository = userRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<User> byId = userRepository.findById(id);
        elasticsearchRestTemplate.get(id, User.class, IndexCoordinates.of("user"));
        byId.ifPresent(u -> log.info("User" + u));
        return byId;
    }

    @Override
    public void save() {
        userRepository.save(new User("1", "John名字"));
        userRepository.save(new User("2", "天涯共此时"));
        userRepository.save(new User("3", "海内存知己天涯若比邻"));
        userRepository.save(new User("4", "半夜睡不着觉把心情哼成歌"));
        userRepository.save(new User("5", "半夜睡不着觉把心情哼成歌"));
        userRepository.save(new User("6", "半夜睡不着觉把心情哼成歌"));
        userRepository.save(new User("7", "半夜睡不着觉把心情哼成歌"));
        userRepository.save(new User("8", "半夜睡不着觉把心情哼成歌"));
    }

    @Override
    public void saveArticle(String id, String userId, String title, String content, Date createTime, Date updateTime) {
        ArticleDocument article = new ArticleDocument(id, userId, title, content, createTime, updateTime, 0);
        elasticsearchRestTemplate.save(article);
    }

    @Override
    public UnifyResponse<ArrayList<ArticleDocument>> searchArticle(String key, int page, int pageSize) {
        page = (page < 0) ? 0 : page;
        pageSize = (pageSize <= 0) ? 10 : pageSize;
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (EmptyCheck.notEmpty(key) && key.length() > 0) {
            HighlightBuilder.Field titleField = new HighlightBuilder.Field("title")
                    .preTags("<font color='red'>")
                    .postTags("</font>");
            HighlightBuilder.Field contentField = new HighlightBuilder.Field("content")
                    .preTags("`")
                    .postTags("`");
            queryBuilder.withHighlightFields(titleField, contentField);

            boolQueryBuilder.must(QueryBuilders.matchQuery("title", key));
            boolQueryBuilder.must(QueryBuilders.matchQuery("content", key));

            queryBuilder.withQuery(boolQueryBuilder);
            long count = elasticsearchRestTemplate.count(queryBuilder.build(), ArticleDocument.class);
            queryBuilder.withPageable(pageRequest);
            SearchHits<ArticleDocument> searchHits = elasticsearchRestTemplate.search(queryBuilder.build(),
                    ArticleDocument.class);

            ArrayList<ArticleDocument> articles = searchHits.getSearchHits().stream()
                    .map(articleDocumentSearchHit -> {
                        ArticleDocument document = new ArticleDocument();
                        document.setId(articleDocumentSearchHit.getContent().getId());
                        document.setUserId(articleDocumentSearchHit.getContent().getUserId());
                        document.setCreateTime(articleDocumentSearchHit.getContent().getCreateTime());
                        document.setUpdateTime(articleDocumentSearchHit.getContent().getUpdateTime());
                        document.setCount(count);
                        String content = articleDocumentSearchHit.getContent().getContent();
                        document.setContent
                                (content.substring(1, content.length() - 1)
                                        .replaceAll("\\\\r\\\\n", "\r\n"));

                        List<String> title = articleDocumentSearchHit.getHighlightFields().get("title");
                        if (EmptyCheck.notEmpty(title)) {
                            document.setTitle(String.join("", title));
                        } else {
                            document.setTitle(articleDocumentSearchHit.getContent().getTitle());
                        }
                        //                        List<String> content = articleDocumentSearchHit.getHighlightFields
                        //                                ().get("content");
                        //                        if (EmptyCheck.notEmpty(content)) {
                        //                            document.setContent(String.join("\n", content).replaceAll
                        //                            ("\\\\r\\\\n", "").trim());
                        //                        } else {
                        //                            document.setContent(articleDocumentSearchHit.getContent()
                        //                                    .getContent().substring(0, 400).replaceAll("\\r\\n", ""));
                        //                        }
                        return document;
                    }).collect(Collectors.toCollection(ArrayList::new));
            return UnifyResponse.ok(articles);
        }
        return UnifyResponse.fail();
    }
}
