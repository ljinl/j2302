package com.softeem.service.impl;

import com.alibaba.fastjson.JSON;
import com.softeem.context.UserContext;
import com.softeem.entity.ECommerceOrder;
import com.softeem.mapper.OrderMapper;
import com.softeem.model.common.ResultInfo;
import com.softeem.model.vo.*;
import com.softeem.service.IECommerceOrderService;
import com.softeem.util.AssertUtil;
import com.softeem.util.ResultInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.ServerResponse;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ECommerceOrderServiceImpl implements IECommerceOrderService {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private HttpServletRequest request;
    // lb: 负载均衡
    // 实现了负载均衡算法的客户端程序
//    @Resource
//    private LoadBalancerClient loadBalancerClient;
//    // 注册中心 客户端对象
//    @Resource
//    private DiscoveryClient discoveryClient;

    @Override
    public ResultInfo createOrder(OrderInfo orderInfo) {
        String token = request.getHeader("token");
        AuthorityUserInfo userInfo = UserContext.getUser();
        AssertUtil.isTrue(orderInfo.getOrderItems().isEmpty(),"未选择要购买的商品信息");
        // 扣减账户金额
        List<Long> ids = orderInfo.getOrderItems().stream().map(OrderInfo.OrderItem::getGoodsId).collect(Collectors.toList());

/*
        ServiceInstance instance = loadBalancerClient.choose("goods-service");
        String goodsHost = instance.getHost();
        int goodsPort = instance.getPort();
*/

        // 查询商品信息
//        String url ="http://"+goodsHost+":"+goodsPort+"/goods/findGoodsByIds";
        String url ="http://goods-service/goods/findGoodsByIds";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("token",token);
        RequestEntity requestEntity0 = new RequestEntity(
                ids,
                headers,
                HttpMethod.POST,
                URI.create(url)
        );
        ResponseEntity<ResultInfo> responseEntity = restTemplate.exchange(
                requestEntity0,
                ResultInfo.class
        );
        ResultInfo body = responseEntity.getBody();
        String arrayStr = JSON.toJSONString(body.getData());
        List<SimpleGoodsInfo> goodsInfos = JSON.parseArray(arrayStr,SimpleGoodsInfo.class);
        //  转换map
        Map<Long, OrderInfo.OrderItem> id2Items = orderInfo.getOrderItems().stream().collect(Collectors.toMap(
                OrderInfo.OrderItem::getGoodsId,
                Function.identity()
        ));
        Long sum =0l ;
        log.info("goodsInfos: [{}]",goodsInfos);
        for(SimpleGoodsInfo goodsInfo : goodsInfos){
            Long count = id2Items.get(goodsInfo.getId()).getCount();
            Integer price = goodsInfo.getPrice();
            sum = sum + count*price;
        }
        // 构建要扣减的账户对象
        BalanceInfo balanceInfo = new BalanceInfo(
                userInfo.getId(),
                Double.parseDouble(sum.toString())
        );
        // 使用注册中心获取服务IP和端口
        // 获取实例
/*        ServiceInstance serviceInstance = discoveryClient.getInstances("balance-service").get(0);
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        URI uri = serviceInstance.getUri();
        log.info("host: [{}], uri:[{}]",host,uri.toString());*/

        // 远程调用账户服务
        RequestEntity requestEntity1 = new RequestEntity(
                balanceInfo,
                headers,
                HttpMethod.POST,
//                URI.create("http://"+host+":"+port+"/balance/deduct-balance")
                URI.create("http://balance-service/balance/deduct-balance")
        );
        ResponseEntity<ResultInfo<BalanceInfo>> responseEntity1 = restTemplate.exchange(requestEntity1, new ParameterizedTypeReference<ResultInfo<BalanceInfo>>() {
        });
        log.info("扣减成功，账户剩余金额为： [{}]",responseEntity1.getBody().getData().getBalance());
        // 扣减商品库存
        // 将当前订单数据转换成要扣减的商品集合
        List<DeductGoodsInventory> goodsInventories = orderInfo.getOrderItems().stream().map(OrderInfo.OrderItem::toDeductGoodsInventory).collect(Collectors.toList());

//        String url1 ="http://"+goodsHost+":"+goodsPort+"/goods/deduct-goods-inventory";
        String url1 ="http://goods-service/goods/deduct-goods-inventory";
        RequestEntity requestEntity2 = new RequestEntity(
                goodsInventories,
                headers,
                HttpMethod.POST,
                URI.create(url)
        );
        ResponseEntity<ResultInfo> responseEntity2 = restTemplate.exchange(
                requestEntity2,
                ResultInfo.class
        );

/*        ResultInfo resultInfo = restTemplate.postForObject("http://"+goodsHost+":"+goodsPort+"/goods/deduct-goods-inventory", goodsInventories, ResultInfo.class);
        log.info("message: [{}]",resultInfo.getData());*/
        log.info("message: [{}]",requestEntity2.getBody());
        ECommerceOrder order = new ECommerceOrder();
        order.setAddressId(0);
        order.setUserId(10);
        order.setOrderDetails(JSON.toJSONString(orderInfo.getOrderItems()));
        orderMapper.saveOrder(order);
        return ResultInfoUtil.buildSuccess(order);
    }
}
