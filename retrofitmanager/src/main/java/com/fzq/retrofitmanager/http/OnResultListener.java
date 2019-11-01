package com.fzq.retrofitmanager.http;

/**
 * <p>在Retrofit中接口会导致泛型擦除，所以这里回调使用Class</p>
 *
 * @author fzq 2018/09/05 15:22
 * @version V1.0.0
 * @name OnResultListener
 */
public class OnResultListener<T> {

    /**
     * 请求成功的情况
     *
     * @param result 需要解析的解析类
     */
    public void onSuccess(T result) {
    }

    /**
     * 请求返回错误
     * 显示错误的msg
     */
    public void onErrorMsg(String result){

    }


    /**
     * 响应成功，但是出错的情况
     * @param code    错误码     0x11对应 JsonSyntaxException
     * @param message 错误信息
     */
    public void onError(int code, String message) {
    }

    /**
     * 请求失败的情况
     *
     * @param message 失败信息
     */
    public void onFailure(String message) {
    }


}
