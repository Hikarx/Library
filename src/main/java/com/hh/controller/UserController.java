package com.hh.controller;

import com.hh.pojo.User;
import com.hh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author hh
 * @Date 2024/4/22 15:10
 * @description:
 */
@RestController
@RequestMapping("/index")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        System.out.println("user: " + user);
        user = userService.authenticate(user.getUsername(), user.getPassword());
        if (user != null) {
            return ResponseEntity.ok().body(user.getUserId());
        }
        return ResponseEntity.badRequest().body("用户名或密码错误");
    }
}
