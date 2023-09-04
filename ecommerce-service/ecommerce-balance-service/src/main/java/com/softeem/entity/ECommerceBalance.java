package com.softeem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ECommerceBalance {
    private Integer id;
    private Integer userId;
    private Double balance;
    private Date createTime;
    private Date updateTime;
}
