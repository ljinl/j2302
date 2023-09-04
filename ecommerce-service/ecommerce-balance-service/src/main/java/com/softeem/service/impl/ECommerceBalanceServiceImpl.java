package com.softeem.service.impl;

import com.softeem.entity.ECommerceBalance;
import com.softeem.mapper.ECommerceBalanceMapper;
import com.softeem.model.common.ResultInfo;
import com.softeem.model.vo.BalanceInfo;
import com.softeem.service.IECommerceBalanceService;
import com.softeem.util.AssertUtil;
import com.softeem.util.ResultInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.Transient;

@Slf4j
@Service
public class ECommerceBalanceServiceImpl implements IECommerceBalanceService {
    @Resource
    private ECommerceBalanceMapper balanceMapper;
    @Override
    @Transactional
    public ResultInfo<BalanceInfo> deductBalance(BalanceInfo balanceInfo) {
        AssertUtil.isTrue(balanceInfo==null,"扣减金额不可为空");
        AssertUtil.isTrue(balanceInfo.getBalance()<=0,"扣减的金额必须大于0");

        int count = balanceMapper.deductBalance(balanceInfo);
        if(count >0){
            log.info("扣减成功！");
            ECommerceBalance newBalance = balanceMapper.findByUserId(balanceInfo.getUserId());
            log.info("账户余额为:[{}]",newBalance.getBalance());
            return ResultInfoUtil.buildSuccess(new BalanceInfo(
                    newBalance.getUserId(),
                    newBalance.getBalance()
            ));
        }
        return ResultInfoUtil.buildError(new BalanceInfo(
                -1,
                0.0
        ),"扣减失败！数据异常");
    }
}
