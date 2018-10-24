package com.fzq.retrofitlib;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fzq.retrofitmanager.http.DataType;
import com.fzq.retrofitmanager.http.HttpClient;
import com.fzq.retrofitmanager.http.OnResultListener;
import com.fzq.retrofitmanager.utils.DownloadUtil;
import com.fzq.retrofitmanager.utils.Utils;
import com.fzq.zxinglib.activity.CaptureActivity;
import com.google.gson.Gson;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;
import org.devio.takephoto.uitl.TakePicUtil;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;


public class MainActivity extends AppCompatActivity implements TakePhoto.TakeResultListener, InvokeListener {

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.init(getApplicationContext());
        initView();
        requestGetData();
        requestPostDatawithoutHeader();
        requestPostHeader();
        requestPostBody();
        requsetPostBean();
        requsetUploadFile();
        loadFile();
    }

    private void initView() {
        findViewById(R.id.main_take_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoObtainCameraPermission();
            }
        });

        findViewById(R.id.main_choose_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoObtainStoragePermission();
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




    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    @Override
    public void takeFail(TResult result, String msg) {}

    @Override
    public void takeCancel() {}

    @Override 
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }
    //获取图片成功回调
    @Override
    public void takeSuccess(TResult result) {
        String filePath = result.getImage().getCompressPath();
        Log.e("takeSuccess",filePath);
//        Glide.with(this).load(new File(result.getImages().get(0).getCompressPath())).into(headiv);
    }

    private void autoObtainStoragePermission() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        TakePicUtil.sampleChoosePic(file,getTakePhoto());
    }

    private void autoObtainCameraPermission() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        TakePicUtil.sampleTakePic(file,getTakePhoto());
    }


}
