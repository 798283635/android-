package com.example.keshe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private TextView tvStatus;
    private TextView tvId;
    private TextView tvEmail;
    private TextView tvSchool;
    private TextView tvSex;
    private TextView tvCre_id;
    private TextView tvPhone;
    private TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tvStatus=findViewById(R.id.tv_status);





        Button btn_start_register_onclick = (Button) findViewById(R.id.btn_start_register);
        btn_start_register_onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button btn_start_login_onclick = (Button)findViewById(R.id.btn_start_signin);
        btn_start_login_onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }



}
