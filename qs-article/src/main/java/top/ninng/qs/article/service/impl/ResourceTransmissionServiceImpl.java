package top.ninng.qs.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import top.ninng.qs.article.clients.UserClient;
import top.ninng.qs.article.config.IdConfig;
import top.ninng.qs.article.entity.Article;
import top.ninng.qs.article.entity.Tag;
import top.ninng.qs.article.mapper.ArticleMapper;
import top.ninng.qs.article.mapper.ArticleTagMapper;
import top.ninng.qs.article.mapper.TagMapper;
import top.ninng.qs.article.service.IResourceTransmission;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.common.utils.EmptyCheck;
import top.ninng.qs.common.utils.IdObfuscator;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author OhmLaw
 * @Date 2023/2/18 14:47
 * @Version 1.0
 */
@Slf4j
@Service
public class ResourceTransmissionServiceImpl implements IResourceTransmission {

    IdObfuscator idObfuscator;
    ArticleMapper articleMapper;
    ArticleTagMapper articleTagMapper;
    TagMapper tagMapper;
    UserClient userClient;
    RestTemplate restTemplate = new RestTemplate();
    String reg = "(?<=message\":\")[\\s\\S]*(?=\"})";
    Pattern pattern = Pattern.compile(reg);

    public ResourceTransmissionServiceImpl(IdObfuscator idObfuscator, ArticleMapper articleMapper,
                                           ArticleTagMapper articleTagMapper, TagMapper tagMapper,
                                           UserClient userClient) {
        this.idObfuscator = idObfuscator;
        this.articleMapper = articleMapper;
        this.articleTagMapper = articleTagMapper;
        this.tagMapper = tagMapper;
        this.userClient = userClient;
    }

    @Override
    public UnifyResponse<String> push(String authorizationCode, String link, String resourceId) {
        long[] realId = idObfuscator.decode(resourceId, IdConfig.ARTICLE_ID);
        if (realId.length > 0) {
            Article article = articleMapper.selectByPrimaryKey(realId[0]);
            if (EmptyCheck.notEmpty(article)) {
                ArrayList<Long> longArrayList = articleTagMapper.selectTagIdByArticleIdLongs(realId[0]);
                ArrayList<String> tagList = new ArrayList<String>();
                for (Long aLong : longArrayList) {
                    Tag tag = tagMapper.selectByPrimaryKey(aLong);
                    tagList.add(tag.getName());
                }
                try {
                    return UnifyResponse.ok(restTemplateSend(authorizationCode, link, article, tagList), null);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return UnifyResponse.fail("推送出错！", null);
                }
            }
        }
        return UnifyResponse.fail("id 错误！", null);
    }

    @Override
    public UnifyResponse<String> receive(String authorizationCode,
                                         int mode,
                                         String title,
                                         String content,
                                         ArrayList<String> tag,
                                         Date date,
                                         String id) {
        // 判断授权
        UnifyResponse<String> stringUnifyResponse = userClient.checkAuthorization(id, authorizationCode);
        if (EmptyCheck.notEmpty(stringUnifyResponse) && stringUnifyResponse.getCode() == 200) {
            // TODO: 接收业务
            long[] userId = idObfuscator.decode(id, IdConfig.USER_ID);
            if (userId.length > 0) {
                if (mode == 0) {
                    // 接受文章
                    Article article = new Article();
                    article.setUserId(Math.toIntExact(userId[0]));
                    article.setCreateTime(new Timestamp(date.getTime()));
                    article.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                    article.setDeleteStatus(false);
                    article.setTitle(title);
                    article.setContent(content);
                    article.setIp("");
                    article.setSite("推送");
                    article.setPageView(0);
                    article.setLikeNum(0);
                    article.setStatus(0);
                    article.setStick(0);
                    article.setMode(article.getContent().contains("<!-- more -->") ? 1 : 0);
                    int result = articleMapper.insert(article);
                    if (result > 0) {
                        return UnifyResponse.ok("已接收！", null);
                    }
                    return UnifyResponse.fail("接收失败！", null);

                }
                return UnifyResponse.ok("接收成功！");
            } else {
                return UnifyResponse.fail("id 错误！", null);
            }
        }
        return UnifyResponse.fail("授权错误！", null);
    }

    public String restTemplateSend(String authorizationCode, String link, Article article, ArrayList<String> tag) throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("authorizationCode", authorizationCode);
        params.add("mode", 0);
        params.add("title", article.getTitle());
        params.add("content", article.getContent());
        params.add("tag", tag);
        params.add("dateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(article.getCreateTime()));

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.exchange(link, HttpMethod.POST, request, String.class);
        String result = new String(Objects.requireNonNull(response.getBody()).getBytes("ISO8859-1"),
                StandardCharsets.UTF_8);
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            result = matcher.group();
        }
        return result;
    }
}
