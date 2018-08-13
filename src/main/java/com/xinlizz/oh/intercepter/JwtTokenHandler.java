package com.xinlizz.oh.intercepter;

import com.xinlizz.oh.controller.BaseController;
import com.xinlizz.oh.utils.JwtTokenUtil;
import com.xinlizz.oh.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JwtTokenHandler 基于jwt的token验证
 *
 * @Author: xinlizz
 * @Date: 2018/7/26
 */
public class JwtTokenHandler extends AbstractTokenHandler {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenHandler.class);

    private static final String TOKEN_STR = "_token";

    @Override
    public boolean tokenHandler(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("The current uri is [%s]", request.getRequestURI());
        if (!(handler instanceof HandlerMethod)) {
            return false;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Object bean = handlerMethod.getBean();
        if (!(bean instanceof BaseController)) {
            throw new RuntimeException("The controller[" + bean.getClass().getSimpleName() + "] must be extends BaseController!");
        }

        Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie : cookies) {
            if (StringUtil.equals(TOKEN_STR, cookie.getName())) {
                token = cookie.getValue();
            }
        }

        if (StringUtil.isEmpty(token)) {
            logger.info("The request has no _token!");
            return false;
        }

        Long loginId = JwtTokenUtil.parseToken(token);
        if (null == loginId) {
            try {
                response.sendRedirect(request.getContextPath() + "/login");
                return true;
            } catch (IOException e) {
                throw new RuntimeException("response send redirect has exception!", e);
            }
        }
        return true;
    }
}
