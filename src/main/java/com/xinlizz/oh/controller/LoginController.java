package com.xinlizz.oh.controller;

import com.xinlizz.oh.dto.LoginInfoDto;
import com.xinlizz.oh.results.ActionResult;
import com.xinlizz.oh.service.login.ILoginService;
import com.xinlizz.oh.utils.StringUtil;
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

    /** 登录页面 */
    private static final String LOGIN_VIEW = "login";

    /** 注册页面 */
    private static final String REGISTE_VIEW = "registe";

    @Autowired
    private ILoginService loginService;

    /**
     * 跳转登录页面
     *
     * @author xinlizz
     * @Date 2018/7/31
     * @Param []
     * @return java.lang.String
     */
    @GetMapping("/login")
    public String login() {
        return LOGIN_VIEW;
    }

    /**
     * 登录
     *
     * @author xinlizz
     * @Date 2018/7/31
     * @Param [loginInfoDto, request] 
     * @return com.xinlizz.oh.results.ActionResult
     */
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

    /**
     * 跳转注册页面
     *
     * @author xinlizz
     * @Date 2018/7/31
     * @Param []
     * @return java.lang.String
     */
    @RequestMapping("/registe")
    public String registe() {
        return REGISTE_VIEW;
    }

    /**
     * 注册
     *
     * @author xinlizz
     * @Date 2018/7/31
     * @Param [loginInfoDto, request] 
     * @return com.xinlizz.oh.results.ActionResult
     */
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

    /**
     * 修改登录密码
     *
     * @author xinlizz
     * @Date 2018/7/31
     * @Param [loginId, oldPwd, newPwd] 
     * @return com.xinlizz.oh.results.ActionResult
     */
    @PostMapping("/modifyPwd")
    @ResponseBody
    public ActionResult modifyLoginPassword(Long loginId, String oldPwd, String newPwd) {
        ActionResult result = new ActionResult();
        LoginInfoDto loginInfoDto = loginService.queryLoginInfoById(loginId);
        if (!StringUtil.equals(oldPwd, loginInfoDto.getLoginPassword())) {
            result.setErrorMsg("旧密码不正确");
            return result;
        }

        loginService.modifyLoginPassword(loginId, newPwd);
        result.setErrorMsg("修改成功");
        return result;
    }
}
