package com.hh.controller;

import com.hh.pojo.CommonResp;
import com.hh.pojo.User;
import com.hh.service.UserService;
import com.hh.uitl.JwtUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public CommonResp<String> login(@RequestBody User user) {
        log.info("user: {}", user);
        String token = userService.login(user);

        return new CommonResp<>(token);
    }
}
