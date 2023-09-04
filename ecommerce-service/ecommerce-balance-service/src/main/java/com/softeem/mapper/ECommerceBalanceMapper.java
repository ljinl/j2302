package com.softeem.mapper;

import com.softeem.entity.ECommerceBalance;
import com.softeem.model.vo.BalanceInfo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ECommerceBalanceMapper {

    @Select("select * from t_ecommerce_balance where user_id = #{userId}")
    ECommerceBalance findByUserId(Integer userId);

    @Update("update t_ecommerce_balance set balance=balance-#{balance},update_time=now() where user_id=#{userId}")
    int deductBalance(BalanceInfo balanceInfo);
}
