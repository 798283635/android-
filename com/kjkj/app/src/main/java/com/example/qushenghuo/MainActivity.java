package com.example.qushenghuo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvStatus;
    private TextView tvId;
    private TextView tvEmail;
    private TextView tvSchool;
    private TextView tvSex;
    private TextView tvCre_id;
    private TextView tvPhone;
    private TextView tvName;

    private static final int REQUEST_REGISTER_CODE=1;
    private static final int REQUEST_SIGNIN_CODE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tvStatus=findViewById(R.id.tv_status);
        tvEmail=findViewById(R.id.tv_email);
        tvId=findViewById(R.id.tv_id);
        tvSchool=findViewById(R.id.tv_school);
        tvSex=findViewById(R.id.tv_sex);
        tvCre_id=findViewById(R.id.tv_cre_id);
        tvPhone=findViewById(R.id.tv_phone);
        tvName=findViewById(R.id.tv_name);
    }
    public void btn_start_login_onclick(View view) {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivityForResult(intent,REQUEST_SIGNIN_CODE);
    }

    public void btn_start_register_onclick(View view) {
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivityForResult(intent,REQUEST_REGISTER_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(intent==null){
            return;
        }
        switch (requestCode){
            case REQUEST_SIGNIN_CODE:
               // intent.setClass(this, Main2Activity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                /*intent.putExtra("id", tvId.getText().toString());
                intent.putExtra("email",tvEmail.getText().toString());
                intent.putExtra("name",tvName.getText().toString());
                intent.putExtra("school",tvSchool.getText().toString());
                intent.putExtra("phone",tvPhone.getText().toString());
                intent.putExtra("cre_id",tvCre_id.getText().toString());
                intent.putExtra("sex",tvSex.getText().toString());*/
                //startActivity(intent);
                break;
          case REQUEST_REGISTER_CODE:
              /*intent.setClass(com.example.qushenghuo.MainActivity.this, Main2Activity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);*/
                break;
        }

    }
}
