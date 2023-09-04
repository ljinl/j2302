package com.softeem.service.impl;


import com.alibaba.fastjson.JSON;
import com.softeem.constant.TableId;
import com.softeem.entity.Address;
import com.softeem.mapper.AddressMapper;
import com.softeem.model.common.ResultInfo;
import com.softeem.model.vo.AddressItem;
import com.softeem.service.IECommerceAddressService;
import com.softeem.util.ResultInfoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户地址表 服务实现类
 * </p>
 *
 * @author author
 * @since 2023-08-22
 */
@Service
public class ECommerceAddressService implements IECommerceAddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    public ResultInfo<List<Address>> getAddressById(List<Long> tableId) {

        return ResultInfoUtil.buildSuccess(addressMapper.findById(tableId));
    }
}
