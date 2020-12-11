package com.fzq.paylib.network;


import com.fzq.paylib.PayParams;

/**
 * 网络访问接口
 * 作者: Created by fzq on 2018/4/26 14:28
 * 邮箱: 15997123593@163.com
 */
public interface NetworkClientInterf {
    interface CallBack<R> {
        void onSuccess(R result);
        void onFailure();
    }

    void get(PayParams payParams, CallBack c);

    void post(PayParams payParams, CallBack c);
}
