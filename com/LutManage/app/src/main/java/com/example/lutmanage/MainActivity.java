package com.example.lutmanage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lutmanage.gson.Datapoints;
import com.example.lutmanage.gson.Datastreams;
import com.example.lutmanage.gson.JsonRootBean;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private  String DeviceID = "578076433";
    private  String ApiKey = "=48w4pwC0XeWAvRrZdJO=7NG2mA=";
    private  String enter_time="19A927FCD in";//onenet平台上对应设备的其中一个数据流的名字
    private  String left_time="19A927FCD out";
    TextView Enter_time;
    TextView Left_time;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Enter_time = (TextView)findViewById(R.id.enter_time);
        Left_time = (TextView)findViewById(R.id.left_time);

        update = (Button)findViewById(R.id.update);

        Intent intent = getIntent();
        String logname = intent.getStringExtra("logname");
        enter_time = logname + " in";
        left_time = logname + " out";
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(logname);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Get_enter_time();
                Get_left_time();
            }
        });


    }

    public void Get_enter_time() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://api.heclouds.com/devices/" + DeviceID + "/datapoints?datastream_id=" + enter_time).header("api-key", ApiKey).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData,1);



                    Log.d("MainActivity",responseData);



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    public void Get_left_time() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://api.heclouds.com/devices/" + DeviceID + "/datapoints?datastream_id=" + left_time).header("api-key", ApiKey).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData,2);





                    Log.d("MainActivity",responseData);



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void parseJSONWithGSON(String jsonData,int flag) {
        JsonRootBean app = new Gson().fromJson(jsonData, JsonRootBean.class);
        List<Datastreams> streams = app.getData().getDatastreams();
        List<Datapoints> points = streams.get(0).getDatapoints();
        int count = app.getData().getCount();//获取数据的数量
        for (int i = 0; i < points.size(); i++) {
            String time = points.get(i).getAt();
            String value = points.get(i).getValue();
            if (flag == 1){
                showResponse(value,1);

            }else {
                showResponse(value,2);
            }
            Log.d("MainActivity",value);
        }

    }

    private void showResponse(final String response, final int flag) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {                // 在这里进行UI操作，将结果显示到界面上
                if(flag == 1) {
                    Enter_time.setText(response);
                }else {
                    Left_time.setText(response);
                }
            }
        });
    }


}
