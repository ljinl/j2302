package com.softeem.service;

import com.softeem.model.common.ResultInfo;
import com.softeem.model.vo.BalanceInfo;

public interface IECommerceBalanceService {

    // 修改后的账户信息  扣减（需要扣减的账户信息）
    ResultInfo<BalanceInfo> deductBalance(BalanceInfo balanceInfo);
}
