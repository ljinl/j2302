package com.softeem.controller;

import com.softeem.model.common.ResultInfo;
import com.softeem.model.vo.DeductGoodsInventory;
import com.softeem.service.IECommerceGoodsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class ECommerceGoodsController {

    @Resource
    private IECommerceGoodsService goodsService;

    @PostMapping("/deduct-goods-inventory")
    public ResultInfo deductInventory(@RequestBody List<DeductGoodsInventory> inventories){
        return goodsService.deductGoodsInventory(inventories);
    }

    @PostMapping("/findGoodsByIds")
    public ResultInfo findGoodsByIds(@RequestBody List<Long> ids){
        return goodsService.findByIds(ids);
    }


}
