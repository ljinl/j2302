package com.softeem.controller;

import com.softeem.model.common.ResultInfo;
import com.softeem.model.vo.BalanceInfo;
import com.softeem.service.IECommerceBalanceService;
import com.softeem.service.impl.ECommerceBalanceServiceImpl;
import com.softeem.util.ResultInfoUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/balance")
public class ECommerceBalanceController {

    @Resource
    private IECommerceBalanceService balanceService;

    @PostMapping("/deduct-balance")
    public ResultInfo<BalanceInfo> deductBalance(@RequestBody BalanceInfo balanceInfo){
        return balanceService.deductBalance(balanceInfo);
    }
}
