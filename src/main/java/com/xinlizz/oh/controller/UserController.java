package com.xinlizz.oh.controller;

import com.xinlizz.oh.dto.LoginInfoDto;
import com.xinlizz.oh.dto.UserInfoDto;
import com.xinlizz.oh.results.ActionResult;
import com.xinlizz.oh.service.user.IUserInfoService;
import com.xinlizz.oh.vo.LoginInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * UserController 用户操作类 
 *
 * @Author: xinlizz
 * @Date: 2018/7/13
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserInfoService userInfoService;

    @Resource
    private RedisTemplate<String, LoginInfoVo> redisTemplate;

    /**
     * 保存用户信息
     *
     * @author xinlizz
     * @Date 2018/7/31
     * @Param [userInfoDto]
     * @return com.xinlizz.oh.results.ActionResult
     */
    @RequestMapping("/save")
    @ResponseBody
    public ActionResult saveUserInfo(UserInfoDto userInfoDto){
        ActionResult result = new ActionResult();
        userInfoService.saveUserInfo(userInfoDto);
        result.setRetValue(userInfoDto);
        return result;
    }

    /**
     * 修改用户信息
     *
     * @author xinlizz
     * @Date 2018/7/31
     * @Param [userInfoDto]
     * @return com.xinlizz.oh.results.ActionResult
     */
    @RequestMapping("/modifyUserInfo")
    @ResponseBody
    public ActionResult modifyUserInfo(UserInfoDto userInfoDto) {
        ActionResult result = new ActionResult();

        // 修改用户信息
        userInfoService.modifyUserInfo(userInfoDto);

        /* 设置缓存里的数据 */
        Long loginId = getLoginId();
        LoginInfoVo loginInfoVo = redisTemplate.opsForValue().get(loginId);
        loginInfoVo.setUserInfoDto(userInfoDto);
        redisTemplate.opsForValue().set(loginId.toString(), loginInfoVo);

        result.setRetValue(userInfoDto);
        return result;
    }

    /**
     * 退出系统
     *
     * @author xinlizz
     * @Date 2018/7/31
     * @Param []
     * @return com.xinlizz.oh.results.ActionResult
     */
    @RequestMapping("/quitApp")
    @ResponseBody
    public ActionResult quitApp() {
        ActionResult result = new ActionResult();
        redisTemplate.delete(getLoginId().toString());
        return result;
    }
}
