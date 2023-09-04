package com.softeem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author LJL
 * @Date 2023:08:23:13:53
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String extraInfo;
    private Date createTime;
    private Date updateTime;
}

