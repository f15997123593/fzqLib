package com.fzq.retrofitlib;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fzq.retrofitmanager.utils.ToastUtils;
import com.fzq.retrofitmanager.utils.Utils;
import com.fzq.utillib.alart.SweetAlertDialog;
import com.fzq.zxinglib.activity.CodeUtils;
import java.util.List;


/**
 * 作者: Created by fzq on 2018/10/24 14:32
 * 邮箱: 15997123593@163.com
 */
public class CapActivity extends AppCompatActivity{

    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;

    public Button button1 = null;
    public Button button2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap);
        Utils.init(this);
        initCrash();
        /**
         * 初始化组件
         */
        initView();
        //初始化权限
//        initPermission();


//        initAlert();
    }

    private void initAlert() {
//        SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//        pDialog.setTitleText("Loading");
//        pDialog.setCancelable(false);
//        pDialog.show();


//        new SweetAlertDialog(this)
//                .setTitleText("Here's a message!")
//                .show();

        new SweetAlertDialog(this)
                .setTitleText("Here's a message!")
                .setContentText("It's pretty, isn't it?")
                .setCancelText("No")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ToastUtils.showShortToast("CONFIRM");
                        sweetAlertDialog.cancel();
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ToastUtils.showShortToast("CANCEL");
                        sweetAlertDialog.cancel();
                    }
                })
                .show();

//        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
//                .setTitleText("Oops...")
//                .setContentText("Something went wrong!")
//                .show();

//        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
//                .setTitleText("Are you sure?")
//                .setContentText("Won't be able to recover this file!")
//                .setConfirmText("Yes,delete it!")
//                .show();

//        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
//                .setTitleText("Good job!")
//                .setContentText("You clicked the button!")
//                .show();

//        new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
//                .setTitleText("Sweet!")
//                .setContentText("Here's a custom image.")
//                .setCustomImage(R.drawable.ic_arrow_back)
//                .show();

//        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
//                .setTitleText("Are you sure?")
//                .setContentText("Won't be able to recover this file!")
//                .setCancelText("No,cancel plx!")
//                .setConfirmText("Yes,delete it!")
//                .showCancelButton(true)
//                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.cancel();
//                    }
//                })
//                .show();

    }

    private void initCrash() {


    }

    /**
     * 初始化组件
     */
    private void initView() {
        button1 = (Button) findViewById(R.id.button1);
        /**
         * 打开默认二维码扫描界面
         *
         * 打开系统图片选择界面
         *
         * 定制化显示扫描界面
         *
         * 测试生成二维码图片
         */
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplication(), CaptureActivity.class);
//                startActivityForResult(intent, REQUEST_CODE);
//                button2.getId();
                initAlert();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(CapActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

        /**
         * 选择系统图片并解析
         */
        else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    //解析系统相册的图片
                    CodeUtils.analyzeBitmap(uri.getPath(), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(CapActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(CapActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }



}
