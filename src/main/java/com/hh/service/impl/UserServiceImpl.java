package com.hh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hh.exception.BorrowException;
import com.hh.exception.BorrowExceptionEnum;
import com.hh.mapper.UserMapper;
import com.hh.pojo.User;
import com.hh.service.UserService;
import com.hh.uitl.JwtUtil;
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
    public String login(User user) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("username", user.getUsername());
        query.eq("password", user.getPassword());
        query.eq("role", user.getRole());
        user = userMapper.selectOne(query);
        System.out.println("user: "+ user);
        if (user == null) {
            throw new BorrowException(BorrowExceptionEnum.INVALID_USERNAME_OR_PASSWORD);
        }
        return JwtUtil.createToken(user.getUserId(), user.getUsername());
    }
}
