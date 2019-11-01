package com.fzq.retrofitlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 作者: Created by fzq on 2019/11/1 11:44
 * 邮箱: 15997123593@163.com
 */
public class ErrorActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        findViewById(R.id.error_msg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(0x62);
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
    }

}
