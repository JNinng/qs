package top.ninng.qs.article.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import top.ninng.qs.article.service.IResourceTransmission;
import top.ninng.qs.common.entity.UnifyResponse;
import top.ninng.qs.common.utils.IdObfuscator;

import java.util.ArrayList;
import java.util.Date;

/**
 * @Author OhmLaw
 * @Date 2023/2/18 15:35
 * @Version 1.0
 */
@RestController
@RequestMapping("/transmission")
public class ResourceTransmissionController {

    IResourceTransmission iResourceTransmission;
    IdObfuscator idObfuscator;

    public ResourceTransmissionController(IResourceTransmission iResourceTransmission, IdObfuscator idObfuscator) {
        this.iResourceTransmission = iResourceTransmission;
        this.idObfuscator = idObfuscator;
    }

    @RequestMapping(value = "/push", method = RequestMethod.POST)
    public UnifyResponse<String> push(
            @RequestParam(value = "authorizationCode") String authorizationCode,
            @RequestParam(value = "link") String link,
            @RequestParam(value = "resourceId") String resourceId) {
        return iResourceTransmission.push(authorizationCode, link, resourceId);
    }

    @RequestMapping(value = "/receive/{id}", method = RequestMethod.POST)
    public UnifyResponse<String> receive(
            @RequestParam(value = "authorizationCode") String authorizationCode,
            @RequestParam(value = "mode") int mode,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "tag") ArrayList<String> tag,
            @RequestParam(value = "dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @PathVariable(value = "id") String id) {
        return iResourceTransmission.receive(authorizationCode, mode, title, content, tag, date, id);
    }
}
