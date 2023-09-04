package com.softeem.service;

import com.softeem.model.common.ResultInfo;
import com.softeem.model.vo.LoginUserInfo;

/**
 * @Author LJL
 * @Date 2023:08:23:13:52
 **/
public interface IUserService {

    ResultInfo<String> login(LoginUserInfo loginUserInfo);
}
