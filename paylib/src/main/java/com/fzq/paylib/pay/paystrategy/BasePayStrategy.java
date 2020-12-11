package com.fzq.paylib.pay.paystrategy;

import com.fzq.paylib.PayParams;
import com.fzq.paylib.PayUtils;


/**
 * 支付策略类抽象类
 * 作者: Created by fzq on 2018/4/26 14:28
 * 邮箱: 15997123593@163.com
 */
public abstract class BasePayStrategy implements PayStrategyInterface{
    protected PayParams mPayParams;
    protected String mPrePayInfo;
    protected PayUtils.PayCallBack mOnPayResultListener;

    public BasePayStrategy(PayParams params, String prePayInfo, PayUtils.PayCallBack resultListener) {
        mPayParams = params;
        mPrePayInfo = prePayInfo;
        mOnPayResultListener = resultListener;
    }

    public abstract void doPay();
}