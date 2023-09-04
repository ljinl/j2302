package com.softeem.service.impl;

import com.softeem.entity.EcommerceGoods;
import com.softeem.mapper.ECommerceGoodsMapper;
import com.softeem.model.common.ResultInfo;
import com.softeem.model.vo.DeductGoodsInventory;
import com.softeem.model.vo.SimpleGoodsInfo;
import com.softeem.service.IECommerceGoodsService;
import com.softeem.util.AssertUtil;
import com.softeem.util.ResultInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ECommerceGoodsServiceImpl implements IECommerceGoodsService {

    @Resource
    private ECommerceGoodsMapper goodsMapper;
    @Override
    public ResultInfo deductGoodsInventory(List<DeductGoodsInventory> inventories) {
        // 多个商品----》修改库存---》持久化到DB
        // 要修改的商品ID集合
        List<Long> ids = inventories.stream().map(DeductGoodsInventory::getGoodsId).collect(Collectors.toList());
        // 查询商品的实体信息
        List<EcommerceGoods> goodsList = goodsMapper.findByIds(ids);
        AssertUtil.isTrue(goodsList.isEmpty(),"为查询到对应的商品信息");
        // 将要扣减的商品信息转换成map
        Map<Long, DeductGoodsInventory> goodsId2Inventories =
                inventories.stream().collect(Collectors.toMap(
                DeductGoodsInventory::getGoodsId,
                Function.identity()   // 使用原对象作为值
        ));
        for(EcommerceGoods goods: goodsList){
            Long count = goodsId2Inventories.get(goods.getId()).getCount();
            AssertUtil.isTrue(count <= 0,"扣减的数量不可小于0");
            goodsMapper.deductInventory(goods.getId(),count);
        }
        return ResultInfoUtil.buildSuccess("处理完成");
    }

    @Override
    public ResultInfo findByIds(List<Long> ids) {
        List<EcommerceGoods> ecommerceGoods = goodsMapper.findByIds(ids);
        // 将商品实体对象转换成vo对象
        List<SimpleGoodsInfo> simpleGoodsInfos =
                ecommerceGoods.stream().map(EcommerceGoods::toSimple).collect(Collectors.toList());

        return ResultInfoUtil.buildSuccess(simpleGoodsInfos);
    }
}
