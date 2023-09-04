package com.softeem.service;

import com.softeem.constant.TableId;
import com.softeem.entity.Address;
import com.softeem.model.common.ResultInfo;
import com.softeem.model.vo.AddressItem;

import java.util.List;


/**
 * <p>
 * 用户地址表 服务类
 * </p>
 *
 * @author author
 * @since 2023-08-22
 */
public interface IECommerceAddressService {

    ResultInfo<List<Address>> getAddressById(List<Long> tableId);

}
