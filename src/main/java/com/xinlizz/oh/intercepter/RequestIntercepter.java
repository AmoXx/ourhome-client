package com.xinlizz.oh.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * RequestIntercepter 请求拦截器
 *
 * @Author: xinlizz
 * @Date: 2018/7/15
 */
@Component
public class RequestIntercepter implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestIntercepter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        AbstractTokenHandler tokenHandler = new RedisTokenHandler();
        return tokenHandler.tokenHandler(request, response, handler);
    }
}
