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

    /**
     * 验证token
     *
     * @return boolean
     *
     * @author xinlizz
     * @Date 2018/7/20
     * @Param [request, response]
     */
    public abstract boolean tokenHandler(HttpServletRequest request, HttpServletResponse response, Object handler);
}
