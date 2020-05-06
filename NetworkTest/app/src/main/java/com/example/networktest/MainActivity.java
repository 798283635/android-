package com.example.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import interfaces.heweather.com.interfacesmodule.bean.weather.*;
import interfaces.heweather.com.interfacesmodule.bean.weather.Weather;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;
import interfaces.heweather.com.interfacesmodule.view.HeConfig;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;
import interfaces.heweather.com.interfacesmodule.bean.weather.lifestyle.Lifestyle;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class MainActivity extends AppCompatActivity {
    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button send = (Button) findViewById(R.id.send_request);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"您点击了按钮",Toast.LENGTH_SHORT).show();
                sendRequestWithOkHttp();
            }
        });

    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("https://free-api.heweather.com/s6/weather?location=yangjiang&key=5cfa71f0523045cbbc2a915848c89ad4").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("MainActivity","is "+responseData);
                    parseJSONWithGSON(responseData);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseJSONWithGSON(String responseData) throws JSONException {
        JSONObject jsonObject = new JSONObject(responseData);
        JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
        String weatherContent = jsonArray.getJSONObject(0).toString();
        Gson gson = new Gson();

        Weather weather = gson.fromJson(weatherContent,Weather.class);

        Log.d("MainActivity","城市是：" + weather.getBasic().getCid().toString());
        Log.d("MainActivity","湿度是：" + weather.getNow().getHum().toString());
        Log.d("MainActivity","温度是：" + weather.getNow().getTmp().toString());
        Log.d("MainActivity","国家是：" +  weather.getBasic().getCnty().toString());
        Log.d("MainActivity","云的状况是：" +  weather.getNow().getCloud().toString());

    }



}
