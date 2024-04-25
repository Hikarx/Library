package com.hh.config;

import com.hh.uitl.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author hh
 * @Date 2024/4/25 13:40
 * @description:
 */

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        String s = request.getRequestURL().toString();
        log.info("url, {}", s);
        if (token == null || token.isEmpty()) {
            log.info("token为空");
            return false;
        }
        boolean validate = JwtUtil.validate(token);
        if (!validate) {
            log.warn("token无效");
            return false;
        }
        return true;
    }
}
