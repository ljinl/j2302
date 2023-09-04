package com.softeem.controller;

import com.softeem.model.common.ResultInfo;
import com.softeem.model.vo.LoginUserInfo;
import com.softeem.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author LJL
 * @Date 2023:08:23:14:10
 **/
@RestController
@RequestMapping("/oauth")
public class AuthorityController {
    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public ResultInfo<String> login(@RequestBody LoginUserInfo loginUserInfo){
        return userService.login(loginUserInfo);
    }

}
