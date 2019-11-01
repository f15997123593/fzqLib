package com.fzq.retrofitmanager.utils;

import android.app.Activity;
import android.content.Context;

/**
 * 作者: Created by fzq on 2019/11/1 13:12
 * 邮箱: 15997123593@163.com
 */
public class ErrorJumpUtils {

    private static Activity aAontext;
    private static Class<? extends Activity> bActivity;

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Activity context,Class<? extends Activity> activity) {
        aAontext = context;
        bActivity = activity;
    }

    public static Activity getAActivity(){
        return aAontext;
    }

    public static Class<? extends Activity> getBActivity(){
        return bActivity;
    }

}
