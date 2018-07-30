package com.xinlizz.oh.intercepter;

import com.xinlizz.oh.vo.LoginInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * RedisTokenHandler 基于redis的token校验
 *
 * @Author: xinlizz
 * @Date: 2018/7/20
 */
public class RedisTokenHandler extends AbstractTokenHandler {

    private static final Logger logger = LoggerFactory.getLogger(RedisTokenHandler.class);

    @Resource
    private RedisTemplate<String, LoginInfoVo> redisTemplate;

    @Override
    public boolean tokenHandler(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("=====url: " + request.getRequestURL());
        logger.info("=====uri: " + request.getRequestURI());
        //判断是否有session
        HttpSession session = request.getSession(false);
        LoginInfoVo loginInfoVo;
        if (null != session && null != (loginInfoVo = (LoginInfoVo) session.getAttribute(LoginInfoVo.SESSION_LOGIN))
                && null != (loginInfoVo = redisTemplate.opsForValue().get(loginInfoVo.getId()))) {
            redisTemplate.opsForValue().set(String.valueOf(loginInfoVo.getId()), loginInfoVo);
            redisTemplate.expire(String.valueOf(loginInfoVo.getId()), 1, TimeUnit.MINUTES);
            return true;
        } else {
            try {
                response.sendRedirect(request.getContextPath() + "/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}
