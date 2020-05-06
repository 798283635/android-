package com.example.materialtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText address;
    private EditText phone;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        address = (EditText)findViewById(R.id.address);
        phone = (EditText)findViewById(R.id.phone);
        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(Customer.isExist() == true)
                LitePal.getDatabase();

                String Username = username.getText().toString();
                String Password = password.getText().toString();
                String Address = address.getText().toString();
                String Phone = phone.getText().toString();
                Customer customer = new Customer();
                customer.setUsername(Username);
                customer.setPassword(Password);
                customer.setAddress(Address);
                customer.setPhone(Phone);
                customer.save();
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                Toast.makeText(RegisterActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });
    }
}
