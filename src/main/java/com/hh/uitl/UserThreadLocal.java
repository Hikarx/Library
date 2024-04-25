package com.hh.uitl;

import com.hh.pojo.User;

/**
 * @Author hh
 * @Date 2024/4/25 19:32
 * @description:
 */
public class UserThreadLocal {
    private static ThreadLocal<User> user = new ThreadLocal<>();

    public static User getUser(){
        return user.get();
    }
    public static void setUser(User user) {
        UserThreadLocal.user.set(user);
    }

    public static Integer getId() {
        return user.get().getUserId();
    }
}
