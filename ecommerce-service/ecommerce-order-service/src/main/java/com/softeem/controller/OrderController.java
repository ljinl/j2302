package com.softeem.controller;

import com.softeem.model.common.ResultInfo;
import com.softeem.model.vo.OrderInfo;
import com.softeem.service.IECommerceOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IECommerceOrderService orderService;


    @PostMapping("/create-order")
    public ResultInfo createOrder(@RequestBody OrderInfo orderInfo){
        return orderService.createOrder(orderInfo);
    }
}
