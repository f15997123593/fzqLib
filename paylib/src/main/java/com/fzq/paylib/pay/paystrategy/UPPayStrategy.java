package com.fzq.paylib.pay.paystrategy;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.fzq.paylib.PayParams;
import com.fzq.paylib.PayUtils;
import com.unionpay.UPPayAssistEx;


/**
 * 支付策略抽象
 * 作者: Created by fzq on 2018/4/26 14:28
 * 邮箱: 15997123593@163.com
 */
public class UPPayStrategy extends BasePayStrategy {
    /*****************************************************************
     * mMode参数解释： "00" - 启动银联正式环境 ;"01" - 连接银联测试环境
     *****************************************************************/
    private final String OFFICIAL_MODE = "00";
    private final String DEV_MODE = "01";
    private final String sMODE = DEV_MODE;
    // TODO 请开发同学根据实际情况修改上述环境mode

    private static final int PLUGIN_VALID = 0;// 有效的响应码
    private static final int PLUGIN_NOT_INSTALLED = -1;// 银联支付插件未安装
    private static final int PLUGIN_NEED_UPGRADE = 2;// 银联支付环境需要升级
    private Context mContext;
    private LocalBroadcastManager mBroadcastManager;
    public static final String WECHAT_PAY_RESULT_ACTION = "com.tencent.mm.opensdk.WECHAT_PAY_RESULT_ACTION";
    public static final String WECHAT_PAY_RESULT_EXTRA = "com.tencent.mm.opensdk.WECHAT_PAY_RESULT_EXTRA";

    public UPPayStrategy(PayParams params, String prePayInfo, PayUtils.PayCallBack resultListener) {
        super(params, prePayInfo, resultListener);
        mContext = params.getActivity();
    }

    @Override
    public void doPay() {
        String tn = getTn(mPrePayInfo);
        int ret = UPPayAssistEx.startPay(mPayParams.getActivity(), null, null, tn, sMODE);
        registPayResultBroadcast();
//        if (ret == PLUGIN_VALID) {
//            mOnPayResultListener.onPayCallBack(PayUtils.COMMON_PAY_OK);
//        } else if (ret == PLUGIN_NOT_INSTALLED) {
//            mOnPayResultListener.onPayCallBack(PayUtils.UPPAY_PLUGIN_NOT_INSTALLED);
//        } else if (ret == PLUGIN_NEED_UPGRADE) {
//            //拒绝
//            mOnPayResultListener.onPayCallBack(PayUtils.UPPAy_PLUGIN_NEED_UPGRADE);
//        } else {
//            mOnPayResultListener.onPayCallBack(PayUtils.COMMON_PAY_ERR);
//        }
    }

    private void registPayResultBroadcast() {
        mBroadcastManager = LocalBroadcastManager.getInstance(mContext.getApplicationContext());
        IntentFilter filter = new IntentFilter(WECHAT_PAY_RESULT_ACTION);
        mBroadcastManager.registerReceiver(mReceiver, filter);
    }

    private void unRegistPayResultBroadcast() {
        if (mBroadcastManager != null && mReceiver != null) {
            mBroadcastManager.unregisterReceiver(mReceiver);
            mBroadcastManager = null;
            mReceiver = null;
        }
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int result = intent.getIntExtra(WECHAT_PAY_RESULT_EXTRA, -100);
            mOnPayResultListener.onPayCallBack(result);
            unRegistPayResultBroadcast();
        }
    };

    private String getTn(String payinfo) {
        // TODO 请根据自身需求解析mPrePayinfo得到预支付订单号tn
        // payinfo
        return payinfo;
    }

}
