package com.xinlizz.oh.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JwtTokenHandler 基于jwt的token验证
 *
 * @Author: xinlizz
 * @Date: 2018/7/26
 */
public class JwtTokenHandler extends AbstractTokenHandler {

    @Override
    public boolean tokenHandler(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return false;
    }
}
