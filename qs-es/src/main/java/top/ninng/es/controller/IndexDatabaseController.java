package top.ninng.es.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import top.ninng.es.entity.ArticleDocument;
import top.ninng.es.entity.User;
import top.ninng.es.service.IndexDatabaseService;
import top.ninng.qs.common.entity.UnifyResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * @Author OhmLaw
 * @Date 2022/9/25 14:54
 * @Version 1.0
 */
@RestController
@RequestMapping("/es/index")
public class IndexDatabaseController {

    IndexDatabaseService indexDatabaseService;

    public IndexDatabaseController(IndexDatabaseService indexDatabaseService) {
        this.indexDatabaseService = indexDatabaseService;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public User getDocumentById(@PathVariable(value = "id") String id) {
        Optional<User> byId = indexDatabaseService.findById(id);
        return byId.orElse(null);
    }

    @RequestMapping(value = "/getLikeArticle", method = RequestMethod.POST)
    public UnifyResponse<ArrayList<ArticleDocument>> getLikeArticle(
            @RequestParam(value = "key") String key,
            @RequestParam(value = "articleId") String articleId,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize) {
        return indexDatabaseService.getLikeArticle(key, page, pageSize, articleId);
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public void save() {
        indexDatabaseService.save();
    }

    @RequestMapping(value = "/saveArticle", method = RequestMethod.POST)
    public void saveArticle(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "title") String title,
            @RequestBody String content,
            @RequestParam(value = "createTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date
                    createTime,
            @RequestParam(value = "updateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date
                    updateTime) {
        indexDatabaseService.saveArticle(id, userId, title, content, createTime, updateTime);
    }

    @RequestMapping(value = "/searchArticle", method = RequestMethod.POST)
    public UnifyResponse<ArrayList<ArticleDocument>> searchArticle(
            @RequestParam(value = "key") String key,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "pageSize") int pageSize) {
        return indexDatabaseService.searchArticle(key, page, pageSize);
    }
}
