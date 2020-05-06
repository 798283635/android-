package com.example.keshe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.litepal.LitePal;

import java.util.List;


public class MyActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,Bundle savedInstanceState) {
        //创建Fragment的布局
        View view = inflater.inflate(R.layout.activity_my,container,false);



        Bundle bundle =this.getArguments();
        TextView tvId = (TextView)view.findViewById(R.id.tv_id);
        TextView tvEmail = (TextView)view.findViewById(R.id.tv_email);
        TextView tvSchool = (TextView)view.findViewById(R.id.tv_school);
        TextView tvSex =  (TextView)view.findViewById(R.id.tv_sex);
        TextView tvCre_id = (TextView)view.findViewById(R.id.tv_cre_id);
        TextView tvPhone =  (TextView)view.findViewById(R.id.tv_phone);
        TextView tvName  = (TextView)view.findViewById(R.id.tv_name);


        String logId = bundle.getString("id");

        List<User> users = LitePal.where("idd = ?", logId).find(User.class);


        if(users.isEmpty() == false){
            for (User user:users){
                if( user.getIdd().equals(logId)==true){

                    tvName.setText("姓名: "  + user.getName());
                    tvSex.setText("性别: "+ user.getSex());
                    tvSchool.setText("学校: "+user.getSchool());
                    tvCre_id.setText("身份证号: "+user.getCard());

                    tvId.setText("学号: "+user.getIdd());
                    tvPhone.setText("手机号码: "+user.getPhone());
                    tvEmail.setText("邮箱: "+user.getEmail());

                    break;
                }
            }
        }



        return view;
    }



}