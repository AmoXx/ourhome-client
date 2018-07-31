package com.xinlizz.oh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * MainController 主页控制类
 *
 * @Author: xinlizz
 * @Date: 2018/7/20
 */
@Controller
public class MainController extends BaseController {

    private static final String MAIN_VIEW = "main";

    /**
     * 跳转首页
     *
     * @author xinlizz
     * @Date 2018/7/31
     * @Param []
     * @return java.lang.String
     */
    @RequestMapping("/main")
    public String toMain(){
        return MAIN_VIEW;
    }
}
