package com.xinlizz.oh.controller;

import com.xinlizz.oh.dto.UserInfoDto;
import com.xinlizz.oh.service.user.IUserInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController 用户操作类 
 *
 * @Author: xinlizz
 * @Date: 2018/7/13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserInfoService userInfoService;

    public UserController(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @RequestMapping("/save")
    public void saveUserInfo(@RequestBody UserInfoDto userInfoDto){
        userInfoService.saveUserInfo(userInfoDto);
    }

    public void modifyUserInfo() {

    }
}
