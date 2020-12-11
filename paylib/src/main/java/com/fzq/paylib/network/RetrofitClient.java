package com.fzq.paylib.network;

import com.fzq.paylib.PayParams;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * retrofit网络请求简单封装
 * 作者: Created by fzq on 2018/4/26 14:28
 * 邮箱: 15997123593@163.com
 */
public class RetrofitClient implements NetworkClientInterf {
    @Override
    public void get(PayParams payParams, final CallBack c) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(payParams.getApiUrl())
                .build();
        PrePayInfoService service = retrofit.create(PrePayInfoService.class);
        Call<ResponseBody> call = service.getPrePayInfo(payParams.getPayRemark().toString(), String.valueOf(payParams.getPrice()), payParams.getPayWay().toString());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string();
                        TnDataJsonBean tnDataJsonBean = new Gson().fromJson(result, TnDataJsonBean.class);
                        if (tnDataJsonBean.getResult()!=null){
                            c.onSuccess(tnDataJsonBean.getResult().getTn());
                        }
                    } catch (IOException | IllegalStateException e) {
                        e.printStackTrace();
                    }
                } else {
                    c.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                c.onFailure();
            }
        });
    }

    @Override
    public void post(PayParams payParams, final CallBack c) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(payParams.getApiUrl())
                .build();
        PrePayInfoService service = retrofit.create(PrePayInfoService.class);

        Call<ResponseBody> call = service.postPrePayInfo(payParams.getPayRemark().toString(), String.valueOf(payParams.getPrice()), payParams.getPayWay().toString());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string();
                        TnDataJsonBean tnDataJsonBean = new Gson().fromJson(result, TnDataJsonBean.class);
                        if (tnDataJsonBean.getResult()!=null){
                            c.onSuccess(tnDataJsonBean.getResult().getTn());
                        }
                    } catch (IOException | IllegalStateException e) {
                        e.printStackTrace();
                    }
                } else {
                    c.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                c.onFailure();
            }
        });
    }
}
