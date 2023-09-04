package com.softeem.service;


import com.softeem.model.common.ResultInfo;
import com.softeem.model.vo.DeductGoodsInventory;

import java.util.List;

public interface IECommerceGoodsService {

    // 多个商品多个数量

    ResultInfo deductGoodsInventory(List<DeductGoodsInventory> inventories);

    ResultInfo findByIds(List<Long> ids);

}
