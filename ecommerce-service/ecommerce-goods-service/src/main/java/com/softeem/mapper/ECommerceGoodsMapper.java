package com.softeem.mapper;

import com.softeem.entity.EcommerceGoods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ECommerceGoodsMapper {
    @Select("<script> " +
            " select * " +
            " from t_ecommerce_goods where  id in " +
            " <foreach item=\"id\" collection=\"ids\" open=\"(\" separator=\",\" close=\")\">" +
            "   #{id}" +
            " </foreach> " +
            " </script>")
    List<EcommerceGoods> findByIds(List<Long> ids);


    @Update("update t_ecommerce_goods set inventory = inventory-#{count} where id = #{goodsId}")
    int deductInventory(@Param("goodsId") Long goodsId,@Param("count") Long count);
}
