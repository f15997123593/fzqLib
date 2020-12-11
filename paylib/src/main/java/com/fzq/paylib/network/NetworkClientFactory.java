package com.fzq.paylib.network;


import com.fzq.paylib.enums.NetworkClientType;

/**
 * 网络访问接口简单工厂
 * 作者: Created by fzq on 2018/4/26 14:28
 * 邮箱: 15997123593@163.com
 */
public class NetworkClientFactory {

    public static NetworkClientInterf newClient(NetworkClientType networkClientType) {
        switch (networkClientType) {
            case HttpUrlConnetion:
                return new HttpUrlConnectionClient();

            case Volley:
                return new VolleyClient();

            case Retrofit:
                return new RetrofitClient();

            case OkHttp:
                return new OkHttpClientImpl();

            default:
                return new HttpUrlConnectionClient();
        }
    }
}
