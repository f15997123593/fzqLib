package com.fzq.retrofitmanager.bean;

/**
 * 日创的网络请求返回数据类型进行封装
 * 将接口返回data为null的情况下的JsonSyntaxException舍弃
 * 直接返回出msg的内容
 * 作者: Created by fzq on 2018/10/18 15:46
 * 邮箱: 15997123593@163.com
 */
public class ErrorMsgBean {

    private String message;

    public String getMessage() {
        return message;
    }


}
