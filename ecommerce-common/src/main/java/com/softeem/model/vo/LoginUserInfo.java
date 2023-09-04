package com.softeem.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LJL
 * @Date 2023:08:23:13:57
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserInfo {
    private String username;
    private String password;
}
