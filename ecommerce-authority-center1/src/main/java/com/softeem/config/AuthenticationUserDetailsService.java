package com.softeem.config;

import com.softeem.entity.User;
import com.softeem.mapper.UserMapper;
import com.softeem.util.AssertUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

@Component
public class AuthenticationUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User currentUser = userMapper.findByUsername(s);
        AssertUtil.isTrue(currentUser == null,"用户未找到");
        return new org.springframework.security.core.userdetails.User(
                currentUser.getUsername(),
                currentUser.getPassword(),
                Collections.emptyList()
        );
    }
}
