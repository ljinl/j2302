package com.softeem.mapper;

import com.softeem.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {


    @Select("select * from t_ecommerce_user where username =#{username}")
    User findByUsername(String username);
}
