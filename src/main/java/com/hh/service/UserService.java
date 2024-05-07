package com.hh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hh.pojo.User;

/**
 * @Author hh
 * @Date 2024/4/22 15:10
 * @description:
 */
public interface UserService extends IService<User> {
    String login(User user);
}
