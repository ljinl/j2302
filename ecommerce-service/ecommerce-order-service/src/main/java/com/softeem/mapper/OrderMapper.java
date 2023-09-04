package com.softeem.mapper;

import com.softeem.entity.ECommerceOrder;
import org.apache.ibatis.annotations.Insert;

public interface OrderMapper {

    @Insert("insert into t_ecommerce_order values(null,#{userId},#{addressId},#{orderDetails},now(),now())")
    int saveOrder(ECommerceOrder order);
}
