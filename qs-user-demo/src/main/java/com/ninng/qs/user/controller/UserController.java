package com.ninng.qs.user.controller;

import com.ninng.qs.user.domain.User;
import com.ninng.qs.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public User selectById(@PathVariable("id") Long id){
        return userService.selectById(id);
    }
}
