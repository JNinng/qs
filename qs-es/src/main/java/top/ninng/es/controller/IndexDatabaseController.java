package top.ninng.es.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ninng.es.domain.User;
import top.ninng.es.service.IndexDatabaseService;
import top.ninng.es.service.UserService;

import java.util.Optional;

/**
 * @Author OhmLaw
 * @Date 2022/9/25 14:54
 * @Version 1.0
 */
@Controller
@ResponseBody
@RequestMapping("/index")
public class IndexDatabaseController {

    private final IndexDatabaseService indexDatabaseService;
    private final UserService userService;

    public IndexDatabaseController(IndexDatabaseService indexDatabaseService, UserService userService) {
        this.indexDatabaseService = indexDatabaseService;
        this.userService = userService;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public User getDocumentById(@PathVariable(value = "id") String id) {
        indexDatabaseService.getDocumentById(id);
        Optional<User> byId = userService.findById(id);
        return byId.orElse(null);
    }
}
