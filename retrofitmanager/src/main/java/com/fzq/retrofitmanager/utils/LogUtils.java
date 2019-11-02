package com.fzq.retrofitmanager.utils;

import android.util.Log;

/**
 * 作者: Created by fzq on 2019/11/2 10:46
 * 邮箱: 15997123593@163.com
 */
public class LogUtils {

    //规定每段显示的长度
    private static int LOG_MAXLENGTH = 4*1000;
    /**
     * Send an {@link Log#ERROR} log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void d(String tag, String msg) {
        if (msg == null){
            return;
        }
        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAXLENGTH;
        for (int i = 0; i < 100; i++) {
            //剩下的文本还是大于规定长度则继续重复截取并输出
            if (strLength > end) {
                Log.d(tag + i, msg.substring(start, end));
                start = end;
                end = end + LOG_MAXLENGTH;
            } else {
                Log.d(tag, msg.substring(start, strLength));
                break;
            }

        }
    }

}
