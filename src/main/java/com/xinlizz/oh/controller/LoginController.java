package com.xinlizz.oh.controller;

import com.xinlizz.oh.dto.LoginInfoDto;
import com.xinlizz.oh.results.ActionResult;
import com.xinlizz.oh.service.login.ILoginService;
import com.xinlizz.oh.vo.LoginInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * LoginController 登录操作
 *
 * @Author: xinlizz
 * @Date: 2018/7/14
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private ILoginService loginService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public ActionResult doLogin(LoginInfoDto loginInfoDto, HttpServletRequest request) throws Exception {
        ActionResult result = new ActionResult();
        if (!validateParam(loginInfoDto)) {
            result.setErrorMsg("请求参数为空！登录失败！");
            return result;
        }
        LoginInfoVo loginInfoVo = loginService.doLogin(loginInfoDto);
        if (null == loginInfoVo) {
            result.setErrorMsg("登录名或密码错误，请重试！");
            return result;
        }
        request.setAttribute(LoginInfoVo.SESSION_LOGIN, loginInfoVo);
        return result;
    }

    @RequestMapping("/registe")
    public String registe() {
        return "registe";
    }

    @PostMapping(value = "/doRegisteLogin")
    @ResponseBody
    public ActionResult doRegiste(@RequestBody LoginInfoDto loginInfoDto, HttpServletRequest request) throws Exception {
        ActionResult result = new ActionResult();
        if (!validateParam(loginInfoDto)) {
            result.setErrorMsg("请填写登录账号和密码！");
            return result;
        }

        if (!loginService.validateLoginNum(loginInfoDto.getLoginNum())) {
            result.setErrorMsg("登录账号为空或者登录账号重复");
            return result;
        }

        loginInfoDto = loginService.registeLoginInfo(loginInfoDto);
        LoginInfoVo loginInfoVo = new LoginInfoVo();
        BeanUtils.copyProperties(loginInfoDto, loginInfoVo);
        result.setRetValue(loginInfoDto);
        request.setAttribute(LoginInfoVo.SESSION_LOGIN, loginInfoVo);
        return result;
    }
}
