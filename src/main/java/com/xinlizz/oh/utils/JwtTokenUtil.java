package com.xinlizz.oh.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xinlizz.oh.dto.LoginInfoDto;
import com.xinlizz.oh.exception.TokenException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * JwtTokenUtil JWT工具
 *
 * @Author: xinlizz
 * @Date: 2018/7/26
 */
public class JwtTokenUtil {

    private static String COUNTRY_NAME = "COUNTRY_NAME";

    private static String APP_NAME = "APP_NAME";

    private static String LOGIN_ID = "loginId";

    private static String LOGIN_NUM = "loginNum";

    private static String ISSUED = "issued";

    public static Algorithm getGenericKey(String loginNum) {
        return Algorithm.HMAC256(loginNum);
    }

    public static Map<String, Object> getHeaders() {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put(COUNTRY_NAME, "ourhome");
        headerMap.put(APP_NAME, "ourhome");
        return headerMap;
    }

    public static String createToken(LoginInfoDto loginInfoDto) {
        try {
            return JWT.create()
                    .withHeader(getHeaders())
                    .withClaim(LOGIN_ID, loginInfoDto.getId())
                    .withClaim(LOGIN_NUM, loginInfoDto.getLoginNum())
                    .withClaim(ISSUED, "ourhome" + UUID.randomUUID().toString())
                    .sign(getGenericKey(loginInfoDto.getLoginNum()));
        } catch (JWTCreationException e) {
            throw new TokenException("token create error!");
        }
    }

    public static Long parseToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaims().get(LOGIN_ID).asLong();
    }
}
