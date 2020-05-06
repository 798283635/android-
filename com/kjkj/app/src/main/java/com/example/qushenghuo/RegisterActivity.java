package com.example.qushenghuo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Nullable
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void btn_submit_register_onclick(View view) {
        EditText etId=findViewById(R.id.et_reg_id);
        EditText etEmail=findViewById(R.id.et_email);
        EditText etPassword=findViewById(R.id.et_reg_password);
        EditText etName=findViewById(R.id.et_name);
        EditText etPhone=findViewById(R.id.et_phone);
        EditText etSex=findViewById(R.id.et_sex);
        EditText etSchool=findViewById(R.id.et_school);
        EditText etCard=findViewById(R.id.et_cre_id);
        User user = new User();
        user.setId(etId.getText().toString());
        user.setPassword(etPassword.getText().toString());
        user.save();  //保存

        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
        intent.putExtra("id",etId.getText().toString());
        intent.putExtra("email",etEmail.getText().toString());
        intent.putExtra("password",etPassword.getText().toString());
        intent.putExtra("name",etName.getText().toString());
        intent.putExtra("school",etSchool.getText().toString());
        intent.putExtra("phone",etPhone.getText().toString());
        intent.putExtra("cre_id",etCard.getText().toString());
        intent.putExtra("sex",etSex.getText().toString());
        setResult(0,intent);
        startActivity(intent);


    }


}
