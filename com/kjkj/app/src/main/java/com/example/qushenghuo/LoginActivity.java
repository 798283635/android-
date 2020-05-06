package com.example.qushenghuo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private DBOpenHelper mDBOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void btn_submit_login_onclick(View view) {
        EditText etId=findViewById(R.id.et_reg_id);
        EditText etPassword=findViewById(R.id.et_reg_password);
        Intent intent=new Intent(this, Main2Activity .class);
       /* intent.putExtra("id",etId.getText().toString());
        intent.putExtra("password",etPassword.getText().toString());
        setResult(0,intent);
        finish();*/
       startActivity(intent);
    }
}
