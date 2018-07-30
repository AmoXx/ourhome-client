package com.xinlizz.oh.controller;

import org.springframework.stereotype.Controller;

/**
 * BaseController 基本操作类
 *
 * @Author: xinlizz
 * @Date: 2018/7/14
 */
@Controller
public class BaseController {

    /** 默认分页初始页面 */
    protected static final String DEFAULT_PAGE = "1";

    /** 默认分页大小 */
    protected static final String DEFAULT_PAGE_SIZE = "10";

    private ThreadLocal<Long> loginIdLocal = new ThreadLocal();

    public Long getLoginId() {
        return loginIdLocal.get();
    }

    public void setLoginId(Long loginId) {
        loginIdLocal.set(loginId);
    }

    protected boolean validateParam(Object obj) {
        if (null == obj) {
            return false;
        }
        return true;
    }
}

