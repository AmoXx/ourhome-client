package com.xinlizz.oh.intercepter;

import com.xinlizz.oh.utils.StringUtil;
import com.xinlizz.oh.vo.LoginInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * RedisSessionHandler 基于redis的token校验
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

        // token判断
        String token = request.getHeader(TOKEN);
        if (StringUtil.isBlank(token)) {
            try {
                response.sendRedirect(request.getContextPath() + "/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
