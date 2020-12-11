package com.fzq.paylib.enums;

/**
 * Created by fzq on 2017/3/11.
 */

public enum PayWay {
    WeChatPay(2),
    ALiPay(3),
    UPPay(0);

    int payWay;
    PayWay(int way) {
        payWay = way;
    }

    @Override
    public String toString() {
        return String.valueOf(payWay);
    }

}
