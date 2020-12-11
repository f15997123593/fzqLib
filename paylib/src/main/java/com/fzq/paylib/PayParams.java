package com.fzq.paylib;

import android.app.Activity;

import com.fzq.paylib.enums.HttpType;
import com.fzq.paylib.enums.NetworkClientType;
import com.fzq.paylib.enums.PayWay;


/**
 * 支付相关参数配置用,build模式
 * 作者: Created by fzq on 2018/4/26 14:28
 * 邮箱: 15997123593@163.com
 */
public class PayParams {
    private Activity mActivity;
    private String mWechatAppID;
    private PayWay mPayWay;
    private float price;
    private String header;
    private String payRemark;
    private HttpType mHttpType = HttpType.Post;
    private NetworkClientType mNetworkClientType = NetworkClientType.OkHttp;
    private String mApiUrl;

    public Activity getActivity() {
        return mActivity;
    }

    private void setActivity(Activity activity) {
        mActivity = activity;
    }

    public String getWeChatAppID() {
        return mWechatAppID;
    }

    private void setWechatAppID(String id) {
        mWechatAppID = id;
    }

    public PayWay getPayWay() {
        return mPayWay;
    }

    private void setPayWay(PayWay mPayWay) {
        this.mPayWay = mPayWay;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public HttpType getHttpType() {
        return mHttpType;
    }

    public float getPrice() {
        return price;
    }

    public String getPayRemark() {
        return payRemark;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark;
    }

    private void setHttpType(HttpType mHttpType) {
        this.mHttpType = mHttpType;
    }

    public NetworkClientType getNetworkClientType() {
        return mNetworkClientType;
    }

    private void setNetworkClientType(NetworkClientType mNetworkClientType) {
        this.mNetworkClientType = mNetworkClientType;
    }

    public String getApiUrl() {
        return mApiUrl;
    }

    private void setApiUrl(String mApiUrl) {
        this.mApiUrl = mApiUrl;
    }

    public static class Builder {
        Activity mActivity;
        String wechatAppId;
        PayWay payWay;
        String header;
        float price;
        String payRemark;
        HttpType httpType;
        NetworkClientType mNetworkClientType;
        String apiUrl;

        public Builder(Activity activity) {
            mActivity = activity;
        }

        public Builder wechatAppID(String appid) {
            wechatAppId = appid;
            return this;
        }

        public Builder payWay(PayWay way) {
            payWay = way;
            return this;
        }

        public Builder addHeader(String head) {
            header = head;
            return this;
        }

        public Builder Price(float Price) {
            price = Price;
            return this;
        }

        public Builder remark(String remark) {
            payRemark = remark;
            return this;
        }


        public Builder httpType(HttpType type) {
            httpType = type;
            return this;
        }

        public Builder httpClientType(NetworkClientType clientType) {
            mNetworkClientType = clientType;
            return this;
        }

        public Builder requestBaseUrl(String url) {
            apiUrl = url;
            return this;
        }

        public PayParams build() {
            PayParams params = new PayParams();
            params.setActivity(mActivity);
            params.setWechatAppID(wechatAppId);
            params.setPayWay(payWay);
            params.setPrice(price);
            params.setPayRemark(payRemark);
            params.setHeader(header);
            params.setHttpType(httpType);
            params.setNetworkClientType(mNetworkClientType);
            params.setApiUrl(apiUrl);
            return params;
        }

    }

}
