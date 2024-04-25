package com.hh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hh.mapper.UserMapper;
import com.hh.pojo.User;
import com.hh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author hh
 * @Date 2024/4/22 15:11
 * @description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User authenticate(User user) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("username", user.getUsername());
        query.eq("password", user.getPassword());
        query.eq("role", user.getRole());
        user = userMapper.selectOne(query);
        if (user != null) {
            return user;
        }
        return null;
    }
}
