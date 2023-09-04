package com.softeem.mapper;

import com.softeem.constant.TableId;
import com.softeem.entity.Address;
import com.softeem.model.vo.AddressItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户地址表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2023-08-22
 */
public interface AddressMapper {


    @Select("<script> " +
            " select * " +
            " from t_ecommerce_address where  id in " +
            "  <foreach item=\"id\" collection=\"tableId\" open=\"(\" separator=\",\" close=\")\">"+
            "  #{id}" +
            "  </foreach> " +
            "</script>")
   List<Address> findById(List<Long> tableId);
}
