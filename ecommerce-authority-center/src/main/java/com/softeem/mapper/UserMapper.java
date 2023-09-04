package com.softeem.mapper;

import com.softeem.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * @Author LJL
 * @Date 2023:08:23:13:53
 **/
public interface UserMapper {

    @Select("select * from t_ecommerce_user where username=#{username}")
    User findByUsername(String username);
}
