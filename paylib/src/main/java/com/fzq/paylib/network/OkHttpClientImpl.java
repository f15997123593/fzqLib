package com.fzq.paylib.network;

import com.fzq.paylib.PayParams;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * okhttp3网络请求简单封装
 * 作者: Created by fzq on 2018/4/26 14:28
 * 邮箱: 15997123593@163.com
 */
public class OkHttpClientImpl implements NetworkClientInterf {

    @Override
    public void get(PayParams payParams, final CallBack c) {
        String baseUrl = payParams.getApiUrl();
        StringBuffer sburl = new StringBuffer();
        // TODO 需要和服务器开发人员协商接口形式需要为：微信，支付宝，银联等 预支付信息走一个接口，通过pay_way或者其他字段进行区分。
        // 以下信息出商品详情介绍(goods_introduction)外，均为必须上传字段，key值由开发者和服务器人员协商自行定义。
        sburl.append(baseUrl)
                .append("?")
                .append("payType=").append(payParams.getPayWay().toString())
                .append("&")
                .append("totalAmount=").append(String.valueOf(payParams.getPrice()))
                .append("&")
                .append("body=").append(payParams.getPayRemark());

        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(sburl.toString()).build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                c.onFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    c.onSuccess(response.body().string());
                } else {
                    c.onFailure();
                }
            }
        });
    }

    @Override
    public void post(PayParams payParams, final CallBack c) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        // TODO 需要和服务器开发人员协商接口形式需要为：微信，支付宝，银联等 预支付信息走一个接口，通过pay_way或者其他字段进行区分。
        // 以下信息出商品详情介绍(goods_introduction)外，均为必须上传字段，key值由开发者和服务器人员协商自行定义。
        RequestBody body = new FormBody.Builder()

                .add("payType", payParams.getPayWay().toString())
                .add("totalAmount", String.valueOf(payParams.getPrice()))
                .add("body", payParams.getPayRemark())
                .build();

        final Request request = new Request.Builder().url(payParams.getApiUrl()).post(body).build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                c.onFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    c.onSuccess(response.body().string());
                } else {
                    c.onFailure();
                }
            }
        });
    }
}
