package com.example.keshe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;


public class RegisterActivity extends AppCompatActivity {

    private EditText etId;
    private EditText etPassword;
    private EditText etSex;
    private EditText etEmail;
    private EditText etCard;
    private EditText etPhone;
    private EditText etName;
    private EditText etSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        LitePal.initialize(this);
        etId= (EditText) findViewById(R.id.et_reg_id);
        etPassword= (EditText) findViewById(R.id.et_reg_password);
        etSex= (EditText) findViewById(R.id.et_sex);
        etEmail= (EditText) findViewById(R.id.et_email);
        etCard= (EditText) findViewById(R.id.et_cre_id);
        etPhone= (EditText) findViewById(R.id.et_phone);
        etName= (EditText) findViewById(R.id.et_name);
        etSchool= (EditText) findViewById(R.id.et_school);




        Button btn_submit_register_onclick = (Button)findViewById(R.id.btn_reg_submit);
        btn_submit_register_onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                User user = new User();
                user.setIdd(etId.getText().toString());
                user.setPassword(etPassword.getText().toString());
                user.setSex(etSex.getText().toString());
                user.setEmail(etEmail.getText().toString());
                user.setCard(etCard.getText().toString());
                user.setPhone(etPhone.getText().toString());
                user.setName(etName.getText().toString());
                user.setSchool(etSchool.getText().toString());
                user.save();


                if (user.save()) {
                    Toast.makeText(RegisterActivity.this, "存储成功", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "存储不成功", Toast.LENGTH_SHORT).show();
                }
                user.saveThrows();
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }



}
