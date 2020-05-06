package com.example.qushenghuo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyActivity extends AppCompatActivity {
    private String tvId;
    private TextView tvEmail;
    private TextView tvSchool;
    private TextView tvSex;
    private TextView tvCre_id;
    private TextView tvPhone;
    private TextView tvName;

    //private static final int REQUEST_REGISTER_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //tvStatus=findViewById(R.id.tv_status);

        User user = new User();
        //tvId=user.getId();
       /* tvSchool=
        tvEmail=
        tvSex=
        tvCre_id=
        tvPhone=
        tvName=  */
        tvName.setText("姓名: "  + user.getId());
        /*tvSex.setText("性别: "+intent.getStringExtra("sex"));
        tvSchool.setText("学校: "+intent.getStringExtra("school"));
        tvCre_id.setText("身份证号: "+intent.getStringExtra("cre_id"));
        tvId.setText("学号: "+intent.getStringExtra("id"));
        tvPhone.setText("手机号码: "+intent.getStringExtra("phone"));
        tvEmail.setText("邮箱: "+intent.getStringExtra("email"));*/

    }




}
