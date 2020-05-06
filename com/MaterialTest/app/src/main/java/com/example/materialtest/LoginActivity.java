package com.example.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.w3c.dom.Text;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.user);
        password = (EditText)findViewById(R.id.key);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Username = username.getText().toString();
                String Password = password.getText().toString();
                String key = null;
                List<Customer> customers = DataSupport.where("username = ?",Username).find(Customer.class);
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
                    //NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
                    //View headerLayout = navView.getHeaderView(0);
                    //TextView username = (TextView) headerLayout.findViewById(R.id.username);
                    //username.setText(Username);
                    Intent intent = new Intent(LoginActivity.this,NewMainActivity.class);
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });


    }
}
