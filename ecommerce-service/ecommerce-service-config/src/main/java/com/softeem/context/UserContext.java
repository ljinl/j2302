package com.softeem.context;

import com.softeem.model.vo.AuthorityUserInfo;

//存上下文
public class UserContext {
    private static final ThreadLocal<AuthorityUserInfo> context = new ThreadLocal<>();

    public static void setUser(AuthorityUserInfo userInfo) {
        context.set(userInfo);
    }

    public static AuthorityUserInfo getUser(){
        return context.get();
    }

    public static void remove(){
        context.remove();
    }

}
