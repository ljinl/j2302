package com.softeem.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.softeem.entity.User;
import com.softeem.mapper.UserMapper;
import com.softeem.model.common.ResultInfo;
import com.softeem.model.vo.AuthorityUserInfo;
import com.softeem.model.vo.LoginUserInfo;
import com.softeem.service.IUserService;
import com.softeem.util.AssertUtil;
import com.softeem.util.JwtUtil;
import com.softeem.util.ResultInfoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author LJL
 * @Date 2023:08:23:13:52
 **/
@Service
public class UserService implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ResultInfo<String> login(LoginUserInfo loginUserInfo) {
        //根据用户名查找用户
        User user = userMapper.findByUsername(loginUserInfo.getUsername());
        AssertUtil.isTrue(user == null, "用户未找到");
        //将输入的密码使用MD5加密进行验证
        String inputPass = MD5.create().digestHex(loginUserInfo.getPassword());
        AssertUtil.isTrue(!user.getPassword().equals(inputPass), "密码错误");
        AuthorityUserInfo userInfo = new AuthorityUserInfo(
                user.getId(),
                user.getUsername()
        );
        Map<String,Object>params = new HashMap<>();
        params.put("userInfo", JSON.toJSONString(userInfo));
        //使用map构建token
        String token = JwtUtil.generate(params);

        return ResultInfoUtil.buildSuccess(token);
    }
}
