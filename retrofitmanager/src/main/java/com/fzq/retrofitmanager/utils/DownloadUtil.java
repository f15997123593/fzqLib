package com.fzq.retrofitmanager.utils;

/**
 * 作者: Created by fzq on 2018/9/18 14:59
 * 邮箱: 15997123593@163.com
 */

import android.os.Environment;
import android.text.TextUtils;
import com.fzq.retrofitmanager.http.HttpClient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者: Created by fzq on 2018/8/25 10:58
 * 邮箱: 15997123593@163.com
 */
public class DownloadUtil {

    private Builder mBuilder;
    private static DownloadUtil downloadUtil;


    public static DownloadUtil newInstance() {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtil();
        }
        return downloadUtil;
    }

    private void setBuilder(Builder builder) {
        this.mBuilder = builder;
    }

    /**
     * post请求下载
     */
    public void download() {
        final Builder builder = mBuilder;
        OkHttpClient okHttpClient  = HttpClient.getOkHttpClient();
        FormBody.Builder formBuild = new FormBody.Builder();
        for (Map.Entry<String, String> entry : builder.params.entrySet()) {
            formBuild.add(entry.getKey(),entry.getValue());
        }
        FormBody formBody  = formBuild.build();
        Request.Builder requestBuild = new Request.Builder();
        requestBuild.url(builder.url);
        if (builder.authorization!=null){
            requestBuild.addHeader(builder.headerKey, builder.authorization);
        }
        Request request = requestBuild.post(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                builder.listener.onDownloadFailed();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(builder.saveDir);
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        builder.listener.onDownloading(progress);
                    }
                    fos.flush();
                    // 下载完成
                    builder.listener.onDownloadSuccess();
                } catch (Exception e) {
                    e.printStackTrace();
                    builder.listener.onDownloadFailed();
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    /**
     * 建造者模式
     */
    public static final class Builder {
        private String url;
        private String authorization;
        private String headerKey;
        private String saveDir;
        private OnDownloadListener listener;
        private Map<String, String> params = new HashMap<>();

        public Builder() {
        }

        /**
         * 请求地址的url，最后会被赋值给HttpClient的静态变量BASE_URL；
         * @param url 请求地址的Url
         */
        public Builder Url(String url) {
            this.url = url;
            return this;
        }


        public Builder addListener(OnDownloadListener listener){
            this.listener = listener;
            return this;
        }

        /**
         * 下载文件路径
         * @param saveDir 路径
         */
        public Builder saveDir(String saveDir) {
            this.saveDir = saveDir;
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
            this.headerKey = "authorization";
            this.authorization = authorization;
            return this;
        }

        /**
         * 添加请求头
         * 应对目前公司的单请求头
         * @param authorization
         * @return
         */
        public Builder addHeader(String key,String authorization){
            this.headerKey = key;
            this.authorization = authorization;
            return this;
        }


        /**
         * @param saveDir
         * @return
         * @throws IOException
         * 判断下载目录是否存在
         */
        private String isExistDir(String saveDir) {
            // 下载位置
            File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
            if (!downloadFile.mkdirs()) {
                try {
                    downloadFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String savePath = downloadFile.getAbsolutePath();
            return savePath;
        }

        public DownloadUtil build() {
            if (TextUtils.isEmpty(url)) {
                throw new UnsupportedOperationException("u must set url...");
            }
            if (TextUtils.isEmpty(saveDir)){
                throw new UnsupportedOperationException("u must set dir...");
            }
            saveDir = isExistDir(saveDir);

            DownloadUtil client = DownloadUtil.newInstance();
            client.setBuilder(this);
            return client;
        }




    }

    /**
     * 监听
     */
    public interface OnDownloadListener {
        /**
         * 下载成功
         */
        void onDownloadSuccess();

        /**
         * @param progress
         * 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onDownloadFailed();
    }

}
