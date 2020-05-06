package com.example.keshe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;


public class LoginActivity extends AppCompatActivity {

    private EditText et_signing_id;
    private EditText et_signing_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_submit_login_onclick = (Button)findViewById(R.id.btn_signing_submit);
        btn_submit_login_onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                et_signing_id = (EditText)findViewById(R.id.et_signing_id);
                et_signing_password = (EditText)findViewById(R.id.et_signing_password);

                String logId = et_signing_id.getText().toString();
                String password = et_signing_password.getText().toString();
                String key="";
                List<User> users = LitePal.where("idd = ?", logId).find(User.class);
                Toast.makeText(LoginActivity.this,password,Toast.LENGTH_SHORT).show();
                if(users.isEmpty() == false){
                    for (User user:users){
                        Log.d("LoginActivity","user.getPassword "+ user.getPassword());
                        if( user.getIdd().equals(logId)==true){
                            key = user.getPassword();
                            Log.d("LoginActivity","user.getPassword "+ user.getPassword());
                            break;
                        }
                    }
                }


                if(key.equals(password) == true){
                    Intent intent=new Intent(LoginActivity.this, Main2Activity.class);
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    intent.putExtra("id",logId);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

}
