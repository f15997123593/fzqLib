package com.fzq.paylib.callback;

/**
 * 支付请求回调
 * 作者: Created by fzq on 2018/4/26 14:28
 * 邮箱: 15997123593@163.com
 */
public interface OnPayInfoRequestListener {
    void onPayInfoRequetStart();

    void onPayInfoRequstSuccess();

    void onPayInfoRequestFailure();
}
