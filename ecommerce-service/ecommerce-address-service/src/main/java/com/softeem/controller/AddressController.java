package com.softeem.controller;


import com.softeem.constant.TableId;
import com.softeem.model.common.ResultInfo;
import com.softeem.service.IECommerceAddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户地址表 前端控制器
 * </p>
 *
 * @author author
 * @since 2023-08-22
 */
@RestController
@RequestMapping("address")
public class AddressController {
    @Resource
    private IECommerceAddressService addressService;
    @GetMapping("/findAddressById")
    public ResultInfo findAddressById(@RequestBody List<Long> tableId){
        return addressService.getAddressById(tableId);
    }

}
