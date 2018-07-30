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

    @RequestMapping("/main")
    public String toMain(){
        return "main";
    }
}
