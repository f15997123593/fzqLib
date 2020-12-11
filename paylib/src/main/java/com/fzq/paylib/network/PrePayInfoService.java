package com.fzq.paylib.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * retrofit请求示例类
 * 作者: Created by fzq on 2018/4/26 14:28
 * 邮箱: 15997123593@163.com
 */
public interface PrePayInfoService {
    // TODO 需要和服务器开发人员协商接口形式需要为：微信，支付宝，银联等 预支付信息走一个接口，通过pay_way或者其他字段进行区分。
    // 以下信息出商品详情介绍(goods_introduction)外，均为必须上传字段，key值由开发者和服务器人员协商自行定义。
    @GET("opc/api/onlinePay")  // TODO 添加实际接口路径
    Call<ResponseBody> getPrePayInfo(@Query("body") String body, @Query("totalAmount") String Price, @Query("payType") String payType);

    @POST("opc/api/onlinePay") // TODO 添加实际接口路径
    Call<ResponseBody> postPrePayInfo(@Query("body") String body, @Query("totalAmount") String Price, @Query("payType") String payType);
}