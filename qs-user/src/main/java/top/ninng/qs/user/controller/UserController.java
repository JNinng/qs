package top.ninng.qs.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ninng.qs.user.entity.User;
import top.ninng.qs.user.service.UserService;

/**
 * @Author OhmLaw
 * @Date 2022/9/15 13:41
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User selectById(@PathVariable("id") Long id) {
        return userService.selectById(id);
    }
}
