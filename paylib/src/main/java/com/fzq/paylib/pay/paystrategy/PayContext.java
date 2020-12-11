package com.fzq.paylib.pay.paystrategy;

/**
 * 支付策略上下文角色
 * 作者: Created by fzq on 2018/4/26 14:28
 * 邮箱: 15997123593@163.com
 */
public class PayContext {
    private PayStrategyInterface mStrategy;

    public PayContext(PayStrategyInterface strategy) {
        mStrategy = strategy;
    }

    public void pay() {
        if (mStrategy != null) {
            mStrategy.doPay();
        }
    }
}
