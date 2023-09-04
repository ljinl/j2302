package com.softeem.entity;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ECommerceOrder {
    private Integer id;
    private Integer userId;
    private Integer addressId;
    private String orderDetails;
    private Date createTime;
    private Date updateTime;
}
