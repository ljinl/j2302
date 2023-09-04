package com.softeem.interceptor;

import com.alibaba.fastjson.JSON;
import com.softeem.context.UserContext;
import com.softeem.model.vo.AuthorityUserInfo;
import com.softeem.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        Claims body = JwtUtil.parse(token);
        String userInfoJSON = body.get("userInfo", String.class);
        AuthorityUserInfo userInfo = JSON.parseObject(userInfoJSON, AuthorityUserInfo.class);
        UserContext.setUser(userInfo);
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(UserContext.getUser() == null){
            UserContext.remove();
        }
    }
}
