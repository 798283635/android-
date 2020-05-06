package com.example.lutmanage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lutmanage.db.Customer;

import org.litepal.LitePal;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = (Button)findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText username = (EditText)findViewById(R.id.user);
                String Username = username.getText().toString();

                EditText password = (EditText)findViewById(R.id.lock);
                String Password = password.getText().toString();
                String key = null;
                List<Customer> customers = LitePal.where("username = ?", Username).find(Customer.class);
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

        //------------------------登录界面---------------//









        //------------------注册界面------------------//

        TextView sign_up  = (TextView)findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


        //------------------注册界面------------------//

    }
}
