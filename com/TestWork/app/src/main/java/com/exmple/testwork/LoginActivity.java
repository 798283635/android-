package com.exmple.testwork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private ImageView bingPicImg;
    private EditText username;
    private EditText password;
    private Button login;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        username = (EditText)findViewById(R.id.user);
        password = (EditText)findViewById(R.id.key);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Username = username.getText().toString();
                String Password = password.getText().toString();
                String key = null;
                List<Customer>customers = DataSupport.where("username = ?",Username).find(Customer.class);
                if(customers.isEmpty() == false){
                    for (Customer customer:customers){
                        if(customer.getUsername().equals(Username)==true){
                            key = customer.getPassword();
                            break;
                        }
                    }
                }


                if(customers.isEmpty() == true ){
                    Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                    Toast.makeText(LoginActivity.this,"请先注册",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else if(Password.equals(key)==false){

                    Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();

                }else{
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    intent.putExtra("logname",Username);
                    startActivity(intent);

                }
            }
        });

        register = (Button)findViewById(R.id.login_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        //loadBingPic();


    }





}
