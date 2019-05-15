package com.xinlizz.oh.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AbstractTokenHandler token处理抽象类-策略模式
 *
 * @Author: xinlizz
 * @Date: 2018/7/20
 */
public abstract class AbstractTokenHandler {

    /** TOKEN */
    protected static final String TOKEN = "Token";

    /**
     * 验证token
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return true/false
     */
    public abstract boolean tokenHandler(HttpServletRequest request, HttpServletResponse response, Object handler);
}
