package com.fzq.paylib.pay.paystrategy;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.fzq.paylib.PayParams;
import com.fzq.paylib.PayUtils;
import com.fzq.paylib.pay.ALPayResult;
import com.fzq.paylib.pay.ALiPayResult;
import com.fzq.paylib.util.ThreadManager;

import java.util.Map;


/**
 * 支付宝策略
 * 作者: Created by fzq on 2018/4/26 14:28
 * 邮箱: 15997123593@163.com
 */
public class ALiPayStrategy extends BasePayStrategy {
    private static final int PAY_RESULT_MSG = 0;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ALPayResult result = new ALPayResult((Map<String, String>) msg.obj);
            if (msg.what != PAY_RESULT_MSG) {
                return;
            }
            ThreadManager.shutdown();
//            ALiPayResult result = new ALiPayResult((Map<String, String>) msg.obj);
            switch (result.getResultStatus()) {
                case ALiPayResult.PAY_OK_STATUS:
                    mOnPayResultListener.onPayCallBack(PayUtils.COMMON_PAY_OK);
                    break;

                case ALiPayResult.PAY_CANCLE_STATUS:
                    mOnPayResultListener.onPayCallBack(PayUtils.COMMON_USER_CACELED_ERR);
                    break;

                case ALiPayResult.PAY_FAILED_STATUS:
                    mOnPayResultListener.onPayCallBack(PayUtils.COMMON_PAY_ERR);
                    break;

                case ALiPayResult.PAY_WAIT_CONFIRM_STATUS:
                    mOnPayResultListener.onPayCallBack(PayUtils.ALI_PAY_WAIT_CONFIRM_ERR);
                    break;

                case ALiPayResult.PAY_NET_ERR_STATUS:
                    mOnPayResultListener.onPayCallBack(PayUtils.ALI_PAY_NET_ERR);
                    break;

                case ALiPayResult.PAY_UNKNOWN_ERR_STATUS:
                    mOnPayResultListener.onPayCallBack(PayUtils.ALI_PAY_UNKNOW_ERR);
                    break;

                default:
                    mOnPayResultListener.onPayCallBack(PayUtils.ALI_PAY_OTHER_ERR);
                    break;
            }
            mHandler.removeCallbacksAndMessages(null);
        }
    };

    public ALiPayStrategy(PayParams params, String prePayInfo, PayUtils.PayCallBack resultListener) {
        super(params, prePayInfo, resultListener);
    }

    @Override
    public void doPay() {
        Runnable payRun = new Runnable() {
            @Override
            public void run() {
                PayTask task = new PayTask(mPayParams.getActivity());
                // TODO 请根据自身需求解析mPrePayinfo，最终的字符串值应该为一连串key=value形式

                Map<String, String> result = task.payV2(mPrePayInfo, true);
                Message message = mHandler.obtainMessage();
                message.what = PAY_RESULT_MSG;
                message.obj = result;
                mHandler.sendMessage(message);
            }
        };
        ThreadManager.execute(payRun);
    }
}
