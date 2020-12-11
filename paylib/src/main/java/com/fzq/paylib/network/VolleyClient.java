package com.fzq.paylib.network;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fzq.paylib.PayParams;

import java.util.HashMap;
import java.util.Map;


/**
 * volley网络请求封装
 * 作者: Created by fzq on 2018/4/26 14:28
 * 邮箱: 15997123593@163.com
 */
public class VolleyClient implements NetworkClientInterf {
    @Override
    public void get(PayParams payParams, final CallBack c) {
        RequestQueue queue = Volley.newRequestQueue(payParams.getActivity());
        String baseUrl = payParams.getApiUrl();
        StringBuffer sburl = new StringBuffer();
        sburl.append(baseUrl)
                .append("?")
                .append("payType=").append(payParams.getPayWay().toString())
                .append("&")
                .append("totalAmount=").append(String.valueOf(payParams.getPrice()))
                .append("&")
                .append("body=").append(payParams.getPayRemark());
        StringRequest request = new StringRequest(Request.Method.GET, sburl.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        c.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                c.onFailure();
            }
        });
        queue.add(request);
    }

    @Override
    public void post(final PayParams payParams, final CallBack c) {
        RequestQueue queue = Volley.newRequestQueue(payParams.getActivity());
        StringRequest request = new StringRequest(Request.Method.POST, payParams.getApiUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        c.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                c.onFailure();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("payType", payParams.getPayWay().toString());
                params.put("totalAmount", String.valueOf(payParams.getPrice()));
                params.put("body", payParams.getPayRemark());
                return params;
            }
        };

        queue.add(request);
    }
}
