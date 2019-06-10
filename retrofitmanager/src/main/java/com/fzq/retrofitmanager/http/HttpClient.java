package com.fzq.retrofitmanager.http;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.fzq.retrofitmanager.utils.FileTypeUtils;
import com.fzq.retrofitmanager.utils.NetworkUtils;
import com.fzq.retrofitmanager.utils.StringUtils;
import com.fzq.retrofitmanager.utils.ToastUtils;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * <p>类说明</p>
 * 记得需要在app中初始化Utils 获取Context对象
 * @author fzq 2018/09/05 15:22
 * @version V1.0.0
 * @name HttpClient
 */
public class HttpClient {

    /*The certificate's password*/
    private static final String STORE_PASS = "6666666";
    private static final String STORE_ALIAS = "666666";
    /*用户设置的BASE_URL*/
    //如果设置了BASE_URL 可以不用使用baseUrl
    private static String BASE_URL = "";
    /*本地使用的baseUrl*/
    private String baseUrl = "";
    private static OkHttpClient okHttpClient;
    private Builder mBuilder;
    private Retrofit retrofit;
    private Call<ResponseBody> mCall;
    private static final Map<String, Call> CALL_MAP = new HashMap<>();

    /**
     * 获取HttpClient的单例
     *
     * @return HttpClient的唯一对象
     */
    private static HttpClient getIns() {
        return HttpClientHolder.sInstance;
    }

    /**
     * 单例模式中的静态内部类写法
     */
    private static class HttpClientHolder {
        private static final HttpClient sInstance = new HttpClient();
    }

    private HttpClient() {
//        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(Utils.getContext()));
        CookieJar cookieJar = new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }
            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };

        //HttpsUtil.SSLParams sslParams = HttpsUtil.getSslSocketFactory(Utils.getContext(), R.raw.cer,STORE_PASS , STORE_ALIAS);
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                //.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                // .hostnameVerifier(HttpsUtil.getHostnameVerifier())
                .addInterceptor(new LoggerInterceptor(null, true))
                .cookieJar(cookieJar)
                .build();
    }

    public static OkHttpClient getOkHttpClient(){
        new HttpClient();
        return okHttpClient;
    }

    public Builder getBuilder() {
        return mBuilder;
    }

    private void setBuilder(Builder builder) {
        this.mBuilder = builder;
    }

    /**
     * 获取的Retrofit的实例，
     * 引起Retrofit变化的因素只有静态变量BASE_URL的改变。
     */
    private void getRetrofit() {
        if (!BASE_URL.equals(baseUrl) || retrofit == null) {
            baseUrl = BASE_URL;
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .build();
        }
    }

    public void post(final OnResultListener onResultListener) {
        Builder builder = mBuilder;
        //添加请求头的情况
        if (builder.authorization!=null){
            if(builder.body!=null){
                //提交body
                mCall = retrofit.create(ApiService.class)
                        .executePost(builder.url,builder.authorization,builder.body);
            }else if (builder.bodyPart!=null&&builder.description!=null){
              //文件上传
                mCall = retrofit.create(ApiService.class)
                        .uploadFile(builder.url,builder.authorization,builder.description,builder.bodyPart);
            } else{
                //标准form表单提交
                mCall = retrofit.create(ApiService.class)
                    .executePost(builder.url,builder.authorization,builder.params);
            }
        }else{
            //无请求头
            if(builder.body!=null){
                //提交body
                mCall = retrofit.create(ApiService.class)
                        .executePost(builder.url,builder.body);
            }else if (builder.bodyPart!=null&&builder.description!=null){
                //文件上传
                mCall = retrofit.create(ApiService.class)
                        .uploadFile(builder.url,builder.description,builder.bodyPart);
            } else{
                //标准form表单提交
                mCall = retrofit.create(ApiService.class)
                        .executePost(builder.url,builder.params);
            }
        }
        putCall(builder, mCall);
        request(builder, onResultListener);
    }


    public void get(final OnResultListener onResultListener) {
        Builder builder = mBuilder;
        if (!builder.params.isEmpty()) {
            String value = "";
            for (Map.Entry<String, String> entry : builder.params.entrySet()) {
                String mapKey = entry.getKey();
                String mapValue = entry.getValue();
                String span = value.equals("") ? "" : "&";
                String part = StringUtils.buffer(span, mapKey, "=", mapValue);
                value = StringUtils.buffer(value, part);
            }
            builder.url(StringUtils.buffer(builder.url, "?", value));
        }
        if (builder.authorization!=null){
            mCall = retrofit.create(ApiService.class).executeGet(builder.url,builder.authorization);
        }else{
            mCall = retrofit.create(ApiService.class).executeGet(builder.url);
        }
        putCall(builder, mCall);
        request(builder, onResultListener);
    }

    private void request(final Builder builder, final OnResultListener onResultListener) {
        if (!NetworkUtils.isConnected()) {
            onResultListener.onFailure("当前网络不可用");
            ToastUtils.showShortToast("当前网络不可用");
            onResultListener.onFailure("当前网络不可用");
            return;
        }
        mCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (200 == response.code()) {
                    try {
                        String result = response.body().string();
                        parseData(result, builder.clazz, builder.bodyType, onResultListener);
                    } catch (IOException | IllegalStateException e) {
                        e.printStackTrace();
                    }
                }
                if (!response.isSuccessful() || 200 != response.code()) {
                    if (response.code() == 500){
                        try {
                            onResultListener.onErrorMsg(DataParseUtil.parseObject(response.errorBody().string()).getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        onResultListener.onError(response.code(), response.message());
                    }
                }
                if (null != builder.tag) {
                    removeCall(builder.url);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                onResultListener.onFailure(t.getMessage());

                if (null != builder.tag) {
                    removeCall(builder.url);
                }
            }

        });
    }


    /**
     * 添加某个请求
     */
    private synchronized void putCall(Builder builder, Call call) {
        if (builder.tag == null)
            return;
        synchronized (CALL_MAP) {
            CALL_MAP.put(builder.tag.toString() + builder.url, call);
        }
    }


    /**
     * 取消某个界面都所有请求，或者是取消某个tag的所有请求;
     * 如果要取消某个tag单独请求，tag需要传入tag+url
     *
     * @param tag 请求标签
     */
    public synchronized void cancel(Object tag) {
        if (tag == null)
            return;
        List<String> list = new ArrayList<>();
        synchronized (CALL_MAP) {
            for (String key : CALL_MAP.keySet()) {
                if (key.startsWith(tag.toString())) {
                    CALL_MAP.get(key).cancel();
                    list.add(key);
                }
            }
        }
        for (String s : list) {
            removeCall(s);
        }
    }

    /**
     * 移除某个请求
     * @param url 添加的url
     */
    private synchronized void removeCall(String url) {
        synchronized (CALL_MAP) {
            for (String key : CALL_MAP.keySet()) {
                if (key.contains(url)) {
                    url = key;
                    break;
                }
            }
            CALL_MAP.remove(url);
        }
    }

    /**
     * Build a new HttpClient.
     * url is required before calling. All other methods are optional.
     */
    public static final class Builder {
        private String builderBaseUrl = "";
        private String url;
        private String authorization;
        private Object tag;
        private RequestBody body;
        private MultipartBody.Part bodyPart;
        private RequestBody description;
        private Map<String, String> params = new HashMap<>();
        /*返回数据的类型,默认是string类型*/
        @DataType.Type
        private int bodyType = DataType.STRING;
        /*解析类*/
        private Class clazz;


        public Builder() {
        }

        /**
         * 请求地址的baseUrl，最后会被赋值给HttpClient的静态变量BASE_URL；
         * @param baseUrl 请求地址的baseUrl
         */
        public Builder baseUrl(String baseUrl) {
            this.builderBaseUrl = baseUrl;
            return this;
        }


        /**
         * 除baseUrl以外的部分，
         * 例如："mobile/login"
         *
         * @param url path路径
         */
        public Builder url(String url) {
            this.url = url;
            return this;
        }

        /**
         * 给当前网络请求添加标签，用于取消这个网络请求
         * 用cancel(tag)移除请求
         * @param tag 标签
         */
        public Builder tag(Object tag) {
            this.tag = tag;
            return this;
        }

        /**
         * 上传json字符串
         * @param jsonStr
         * @return
         */
        public Builder params(String jsonStr){
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonStr);
            this.body = body;
            return this;
        }

        /**
         * 上传JSONObject
         * @param result
         * @return
         */
        public Builder params(JSONObject result){
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), result.toString());
            this.body = body;
            return this;
        }

        //文件上传
        public Builder upload(File file){
            upload(file,"File","File");
            return this;
        }

        //上传文件
        public Builder upload(File file,String name,String fileDes){
            if (file==null){
                throw new UnsupportedOperationException("u can't upload null file");
            }
            //判断文件类型  文件类型和后台一致
            if (FileTypeUtils.fileType(file.getName()) == FileTypeUtils.FileType.None){
                throw new UnsupportedOperationException("u can't upload null file");
            }else if (FileTypeUtils.fileType(file.getName()) == FileTypeUtils.FileType.Doc){
                upload(file,"text/plain",name,fileDes);
            }else if (FileTypeUtils.fileType(file.getName()) == FileTypeUtils.FileType.Pic){
                upload(file,"image/*",name,fileDes);
            }else if (FileTypeUtils.fileType(file.getName()) == FileTypeUtils.FileType.Video){
                upload(file,"application/octet-stream",name,fileDes);
            }else if(FileTypeUtils.fileType(file.getName()) == FileTypeUtils.FileType.Music){
                upload(file,"audio/*",name,fileDes);
            }else {
                upload(file,"application/octet-stream",name,fileDes);
            }
            return this;
        }

        /**
         *  name 是
         * @param file
         * @param fileType
         * @param name
         * @param fileDes
         * @return
         */
        public Builder upload(File file,String fileType,String name,String fileDes){
            RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), fileDes);
            RequestBody requestFile = RequestBody.create(MediaType.parse(fileType), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData(name, file.getName(), requestFile);
            this.description = description;
            this.bodyPart = body;
            return this;
        }

        //上传文件
        public Builder upload(RequestBody description,MultipartBody.Part bodyPart){
            this.description = description;
            this.bodyPart = bodyPart;
            return this;
        }

        //上传RequestBody
        public Builder params(RequestBody body){
            this.body = body;
            return this;
        }

        /**
         * 对于form表单提交
         * 添加请求参数
         * @param key   键
         * @param value 值
         */
        public Builder params(String key, String value) {
            this.params.put(key, value);
            return this;
        }



        /**
         * 添加请求头
         * 应对目前公司的单请求头
         * @param authorization
         * @return
         */
        public Builder addHeader(String authorization){
            this.authorization = authorization;
            return this;
        }

        /**
         * 响应体类型设置,如果要响应体类型为STRING，请不要使用这个方法
         * @param bodyType 响应体类型，分别:STRING，JSON_OBJECT,JSON_ARRAY
         * @param clazz    指定的解析类
         * @param <T>      解析类
         */
        public <T> Builder bodyType(@DataType.Type int bodyType, @NonNull Class<T> clazz) {
            this.bodyType = bodyType;
            this.clazz = clazz;
            return this;
        }

        public HttpClient build() {
            if (!TextUtils.isEmpty(builderBaseUrl)) {
                BASE_URL = builderBaseUrl;
            }
            HttpClient client = HttpClient.getIns();
            client.getRetrofit();
            client.setBuilder(this);
            return client;
        }
    }

    /**
     * 数据解析方法
     *
     * @param data             要解析的数据
     * @param clazz            解析类
     * @param bodyType         解析数据类型
     * @param onResultListener 回调方数据接口
     */
    @SuppressWarnings("unchecked")
    private void parseData(String data, Class clazz, @DataType.Type int bodyType, OnResultListener onResultListener) {
        switch (bodyType) {
            case DataType.STRING:
                onResultListener.onSuccess(data);
                break;
            case DataType.JSON_OBJECT:
                if (DataParseUtil.parseObject(data, clazz)!=null){
                    onResultListener.onSuccess(DataParseUtil.parseObject(data, clazz));
                }else{ //解析异常
                    onResultListener.onError(0x11,data);
                    onResultListener.onErrorMsg(DataParseUtil.parseObject(data).getMessage());
                }
                break;
            case DataType.JSON_ARRAY:
                onResultListener.onSuccess(DataParseUtil.parseToArrayList(data, clazz));
                break;
            default:
                Log.e("HttpClient","http parse tip:if you want return object, please use bodyType() set data type");
                break;
        }
    }

}