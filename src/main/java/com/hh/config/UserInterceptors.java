package com.hh.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hh.pojo.User;
import com.hh.uitl.JwtUtil;
import com.hh.uitl.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author hh
 * @Date 2024/4/25 19:31
 * @description:
 */
@Component
@Slf4j
public class UserInterceptors  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(token)) {
            JSONObject json = JwtUtil.getJSONObject(token);
            log.info("当前登录用户：{}", json);
            UserThreadLocal.setUser(JSONUtil.toBean(json, User.class));
        }
        return true;
    }
}
