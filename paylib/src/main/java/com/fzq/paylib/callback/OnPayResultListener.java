package com.fzq.paylib.callback;


import com.fzq.paylib.enums.PayWay;

/**
 * 支付结果回调
 * 作者: Created by fzq on 2018/4/26 14:28
 * 邮箱: 15997123593@163.com
 */
public interface OnPayResultListener {
    void onPaySuccess(PayWay payWay);

    void onPayCancel(PayWay payWay);

    void onPayFailure(PayWay payWay, int errCode);
}
