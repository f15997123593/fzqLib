package com.fzq.retrofitmanager.http;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>服务端响应的数据类型</p>
 *
 * @author fzq 2018/09/05 15:22
 * @version V1.2.0
 * @name DataType
 */
public class DataType {

    /*返回数据为String*/
    public static final int STRING = 1;
    /*返回数据为json对象*/
    public static final int JSON_OBJECT = 3;
    /*返回数据为json数组*/
    public static final int JSON_ARRAY = 4;

    /**
     * 自定义一个状态注解
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STRING, JSON_OBJECT, JSON_ARRAY})
    public @interface Type {
    }

}