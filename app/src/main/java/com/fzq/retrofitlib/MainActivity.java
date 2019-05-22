package com.fzq.retrofitlib;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.fzq.retrofitmanager.utils.ToastUtils;
import com.fzq.retrofitmanager.utils.Utils;
import com.fzq.utillib.alart.SweetAlertDialog;
import com.fzq.utillib.permission.PermissionHelper;
import com.fzq.utillib.permission.PermissionInterface;

public class MainActivity extends AppCompatActivity implements PermissionInterface {

    private PermissionHelper mPermissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //U SHOW INIT UTILS FIRST
        Utils.init(getApplicationContext());
        initView();

        //初始化并发起权限申请
        mPermissionHelper = new PermissionHelper(this, this);

    }

    private void initView() {
        findViewById(R.id.main_take_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CaptureChooseActivity.class));
            }
        });

        findViewById(R.id.main_video_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shootVideo();
            }
        });

    }

    //show Dialog
    private void shootVideo() {
        new SweetAlertDialog(this)
                .setTitleText("Here's a message!")
                .setConfirmText("shoot")
                .setContentText("Click shoot")
                .setCancelText("No")
                .showCancelButton(true)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mPermissionHelper.requestPermissions();
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
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101) {
            Log.i("CJT", "picture");
            String path = data.getStringExtra("path");
            Log.i("path", ""+path);
            //photo.setImageBitmap(BitmapFactory.decodeFile(path));
        }
        if (resultCode == 102) {
            Log.i("CJT", "video");
            String path = data.getStringExtra("path");
            String url = data.getStringExtra("url");
            Log.i("CJT", ""+path);
            Log.i("CJT", ""+url);
        }
        if (resultCode == 103) {
            Toast.makeText(this, "请检查相机权限~", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(mPermissionHelper.requestPermissionsResult(requestCode, permissions, grantResults)){
            //权限请求结果，并已经处理了该回调
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public int getPermissionsRequestCode() {
        //设置权限请求requestCode，只有不跟onRequestPermissionsResult方法中的其他请求码冲突即可。
        return 10000;
    }

    @Override
    public String[] getPermissions() {
        //设置该界面所需的全部权限
        return new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA
        };
    }

    @Override
    public void requestPermissionsSuccess() {
        //权限请求用户已经全部允许
        startActivityForResult(new Intent(MainActivity.this, ShootActivity.class), 100);
    }

    @Override
    public void requestPermissionsFail() {
        //权限请求不被用户允许。可以提示并退出或者提示权限的用途并重新发起权限申请。
        new SweetAlertDialog(this)
                .setTitleText("请给我权限!")
                .setConfirmText("给我权限")
                .setContentText("权限")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.cancel();
                    }
                }).show();
    }


}
