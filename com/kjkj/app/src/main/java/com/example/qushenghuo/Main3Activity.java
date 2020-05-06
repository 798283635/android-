package com.example.qushenghuo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        /**
         * 一定 一定 一定记得加这句，而且是固定位置，在setContentView()之下
         * 否则无论写的什么逻辑  都不会在Activity中起作用
         */
        ButterKnife.bind(this);
    }

    @BindView(R.id.bt_main_logout)
    Button mBtMainLogout;

    @OnClick({
            R.id.bt_main_logout
    })
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.bt_main_logout:
                Intent intent = new Intent(this, login_exampleActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
