package com.fzq.retrofitlib;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fzq.retrofitmanager.http.DataType;
import com.fzq.retrofitmanager.http.HttpClient;
import com.fzq.retrofitmanager.http.OnResultListener;
import com.fzq.retrofitmanager.utils.DownloadUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * 作者: Created by fzq on 2019/5/22 19:35
 * 邮箱: 15997123593@163.com
 */
public class RetrofitActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void requestLogin() {

        HttpClient client = new HttpClient.Builder()
                .baseUrl("http://192.168.1.100:7009/")
                .url("uac/auth/form")
                .addHeader("Basic Y21ueS1jbGllbnQtdWFjOmNtbnlDbGllbnRTZWNyZXQ=")
                .params("username","godChis")
                .params("password","Cc123456")
                .bodyType(DataType.JSON_OBJECT, LoginJsonBean.class)
                .build();
        client.post(new OnResultListener<LoginJsonBean>(){
            @Override
            public void onSuccess(LoginJsonBean result) {
                Log.e("onSuccess",result.getMessage());
            }

            @Override
            public void onErrorMsg(String result) {
                super.onErrorMsg(result);
                Log.e("onErrorMsg ","result == "+result);
            }
            @Override
            public void onError(int code, String message) {
                super.onError(code, message);
                Log.e("onError code","code == "+code);
                Log.e("onError message","message=="+message);
            }

            @Override
            public void onFailure(String message) {
                super.onFailure(message);
                Log.e("onFailure",message);
            }
        });
    }
    /**
     * 文件下载
     */
    private void loadFile() {
        String mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        //在你本地SD卡根目录文件
        mFileName += "/123.txt";
        DownloadUtil build = new DownloadUtil.Builder()
                .Url("http://www.51cs8.com/NursingCloud/Watch/selectFileById")
                .addHeader("authorization","24_a22270dd43a94f8eb09fb76bc86c7aeb")
                .params("id", "12")
                .saveDir(mFileName)
                .addListener(new DownloadUtil.OnDownloadListener() {
                    @Override
                    public void onDownloadSuccess() {
                    }
                    @Override
                    public void onDownloading(int progress) {
                    }
                    @Override
                    public void onDownloadFailed() {
                    }
                }).build();
        build.download();

    }
    /**
     * 文件上传 成功
     */
    private void requsetUploadFile() {
        String mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        //在你本地SD卡根目录输出录音文件
        mFileName += "/record.amr";
        File file = new File(mFileName);
        HttpClient client = new HttpClient.Builder()
                .baseUrl("http://www.51cs8.com/NursingCloud/")
                .url("Resource/ResourceAdd")
                .tag("upload")
                .addHeader("24_a22270dd43a94f8eb09fb76bc86c7aeb")
                .upload(file)
                .bodyType(DataType.JSON_OBJECT,MsgBean.class)
                .build();
        client.post(new OnResultListener<MsgBean>(){
            @Override
            public void onSuccess(MsgBean result) {
                Log.e("onSuccess",result.getMessage());
            }
        });

    }
    private void requsetPostBean() {
        Gson gs = new Gson();
        MsgBean bean = new MsgBean();
        bean.setMessage("酱油");
        bean.setStatus(true);
        String jsonStr = gs.toJson(bean);//把对象转为JSON格式的字符串
        HttpClient client2 = new HttpClient.Builder()
                .baseUrl("http://www.51cs8.com/NursingCloud/")
                .url("Room/floorAddRoom")
                .addHeader("24_a22270dd43a94f8eb09fb76bc86c7aeb")
                .params(jsonStr)
                .bodyType(DataType.JSON_OBJECT, MsgBean.class)
                .build();
        client2.post(new OnResultListener<MsgBean>(){
            @Override
            public void onSuccess(MsgBean result) {
                Log.e("onSuccess",result.getMessage());
            }
        });

    }
    /**
     * 上传json字符串
     */
    private void requestPostBody() {
        JSONObject result = new JSONObject();
        try {
            result.put("buildingId", 0);
            result.put("floorNum", 0);
            result.put("roomNum", 0);
            result.put("roomDircation", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpClient client = new HttpClient.Builder()
                .baseUrl("http://www.51cs8.com/NursingCloud/")
                .url("Room/floorAddRoom")
                .addHeader("24_a22270dd43a94f8eb09fb76bc86c7aeb")
                .params(result)
                .bodyType(DataType.JSON_OBJECT, MsgBean.class)
                .build();
        client.post(new OnResultListener<MsgBean>(){
            @Override
            public void onSuccess(MsgBean result) {
                Log.e("onSuccess",result.getMessage());
            }
        });
    }

    private void requestPostHeader() {
        HttpClient client = new HttpClient.Builder()
                .baseUrl("http://www.51cs8.com/NursingCloud/")
                .url("Family/oldmanShowAll")
                .addHeader("24_a22270dd43a94f8eb09fb76bc86c7aeb")
                .bodyType(DataType.JSON_OBJECT, MsgBean.class)
                .build();
        client.post(new OnResultListener<MsgBean>(){
            @Override
            public void onSuccess(MsgBean result) {
                Log.e("onSuccess",result.getMessage());
            }

            @Override
            public void onFailure(String message) {
                Log.e("onFailure",message);
            }

            @Override
            public void onError(int code, String message) {
                Log.e("onError",message);
            }
        });
    }

    private void requestPostDatawithoutHeader() {
        HttpClient client = new HttpClient.Builder()
                .baseUrl("http://www.51cs8.com/NursingCloud/")
                .url("token")
                .params("username","15997123593")
                .params("password","111111")
                .bodyType(DataType.JSON_OBJECT, MsgBean.class)
                .build();
        client.post(new OnResultListener<MsgBean>(){
            @Override
            public void onSuccess(MsgBean result) {
                Log.e("onSuccess",result.getMessage());
            }
        });

    }

    private void requestGetData() {
        HttpClient client = new HttpClient.Builder()
                .baseUrl("http://news.at.zhihu.com/api/4/news/before/")
                .url("20170225")
                .bodyType(DataType.JSON_OBJECT, SoceList.class)
                .build();
        client.get(new OnResultListener<SoceList>() {
            @Override
            public void onSuccess(SoceList result) {
                Log.e("onSuccess",result.getDate());
            }

            @Override
            public void onError(int code, String message) {
                Log.e("onError",message);
            }

            @Override
            public void onFailure(String message) {
                Log.e("onFailure",message);
            }
        });
    }

}
