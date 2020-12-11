package com.fzq.paylib.network;

import com.fzq.paylib.PayParams;
import com.fzq.paylib.util.ThreadManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络请求get
 * 作者: Created by fzq on 2018/4/26 14:28
 * 邮箱: 15997123593@163.com
 */
public class HttpUrlConnectionClient implements NetworkClientInterf {

    public void get(final PayParams payParams, final CallBack callBack) {
        Runnable command = new Thread() {
            @Override
            public void run() {
                super.run();
                String apiUrl = payParams.getApiUrl();
                URL url = null;
                HttpURLConnection connection = null;
                InputStream inputStream = null;
                try {
                    StringBuffer sburl = new StringBuffer();
                    // TODO 需要和服务器开发人员协商接口形式需要为：微信，支付宝，银联等 预支付信息走一个接口，通过pay_way或者其他字段进行区分。
                    // 以下信息出商品详情介绍(goods_introduction)外，均为必须上传字段，key值由开发者和服务器人员协商自行定义。
                    sburl.append(apiUrl)
                            .append("?")
                            .append("payType=").append(payParams.getPayWay().toString())
                            .append("&")
                            .append("totalAmount=").append(String.valueOf(payParams.getPrice()))
                            .append("&")
                            .append("body=").append(payParams.getPayRemark());
                    url = new URL(sburl.toString());
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(20 * 1000);
                    connection.setReadTimeout(10 * 1000);

                    connection.connect();

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        inputStream = connection.getInputStream();
                        byte[] data = new byte[512];
                        int len = 0;
                        StringBuffer sb = new StringBuffer();
                        while ((len = inputStream.read(data)) > 0) {
                            sb.append(new String(data, 0, len));
                        }
                        callBack.onSuccess(sb.toString());
                    } else {
                        callBack.onFailure();
                    }
                } catch (Exception e) {
                    callBack.onFailure();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        ThreadManager.execute(command);
    }

    public void post(final PayParams payParams, final CallBack callBack) {
        Runnable command = new Thread() {
            @Override
            public void run() {
                super.run();
                String apiUrl = payParams.getApiUrl();
                URL url = null;
                HttpURLConnection connection = null;
                InputStream inputStream = null;
                OutputStream outputStream = null;
                // TODO 需要和服务器开发人员协商接口形式需要为：微信，支付宝，银联等 预支付信息走一个接口，通过pay_way或者其他字段进行区分。
                // 以下信息出商品详情介绍(goods_introduction)外，均为必须上传字段，key值由开发者和服务器人员协商自行定义。
                try {
                    url = new URL(apiUrl);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(20 * 1000);
                    connection.setReadTimeout(10 * 1000);
                    connection.setDoOutput(true);

                    outputStream = connection.getOutputStream();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer
                            .append("payType=").append(payParams.getPayWay())
                            .append("&")
                            .append("totalAmount=").append(payParams.getPrice())
                            .append("&")
                            .append("body=").append(payParams.getPayRemark());
                    String params = stringBuffer.toString();
                    outputStream.write(params.getBytes());
                    outputStream.flush();

                    connection.connect();

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        inputStream = connection.getInputStream();
                        byte[] data = new byte[512];
                        int len = 0;
                        StringBuffer sb = new StringBuffer();
                        while ((len = inputStream.read(data)) > 0) {
                            sb.append(new String(data, 0, len));
                        }
                        callBack.onSuccess(sb.toString());
                    } else {
                        callBack.onFailure();
                    }
                } catch (Exception e) {
                    callBack.onFailure();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        ThreadManager.execute(command);
    }
}
