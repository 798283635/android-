package com.example.gsontest;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gsontest.gsonAir.Air;
import com.example.gsontest.gsonWeather.Forecast;
import com.example.gsontest.gsonWeather.Lifestyle;
import com.example.gsontest.gsonWeather.Weather;
import com.example.gsontest.util.HttpUtil;
import com.example.gsontest.util.Utility;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private ImageView bingPicImg;
    public DrawerLayout drawerLayout;
    private Button navButton;
    private SwipeRefreshLayout swipeRefresh;
    private String mWeatherId;
   // private String mAirId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navButton = (Button)findViewById(R.id.nav_button);
        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather",null);
        String airString = prefs.getString("air",null);
        if(weatherString != null && airString != null){
            Weather weather = Utility.handleWeatherResponse(weatherString);
            Air air = Utility.handleAirResponse(airString);
            mWeatherId = weather.basic.parent_city;
            showWeatherInfo(weather,air);
        }else{
            mWeatherId = getIntent().getStringExtra("weather_id");
            requestWeatherAir(mWeatherId);

        }
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestWeatherAir(mWeatherId);
            }
        });
        bingPicImg = (ImageView) findViewById(R.id.bing_pic_img);

        String bingPic = prefs.getString("bing_pic",null);
        if(bingPic != null){
            Glide.with(this).load(bingPic).into(bingPicImg);
        }

        //String weatherId = getIntent().getStringExtra("weather_id");
        //requestWeatherAir(weatherId);
        navButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }



    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void requestWeatherAir(final String City) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //-----天气信息
                    OkHttpClient client = new OkHttpClient();
                    String urlWeather = "https://free-api.heweather.com/s6/weather?location="+City+"&key=5cfa71f0523045cbbc2a915848c89ad4";
                    Request request = new Request.Builder().url(urlWeather).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
                    String weatherContent = jsonArray.getJSONObject(0).toString();
                    Weather weather = new Gson().fromJson(weatherContent , Weather.class);
                    String value = weather.status;
                    Forecast forecast = weather.forecastList.get(0);
                    Lifestyle lifestyle1 = weather.lifestyles.get(0);
                    Lifestyle lifestyle2 = weather.lifestyles.get(1);
                    Lifestyle lifestyle3 = weather.lifestyles.get(2);

                    //---空气质量信息
                    String urlAir = "https://free-api.heweather.com/s6/air?location="+City+"&key=5cfa71f0523045cbbc2a915848c89ad4";
                    //request = new Request.Builder().url("https://free-api.heweather.com/s6/air?location=yangjiang&key=5cfa71f0523045cbbc2a915848c89ad4").build();
                    request = new Request.Builder().url(urlAir).build();
                    response = client.newCall(request).execute();
                    responseData = response.body().string();
                    jsonObject = new JSONObject(responseData);
                    jsonArray = jsonObject.getJSONArray("HeWeather6");
                    String AirContent = jsonArray.getJSONObject(0).toString();
                    Air air = new Gson().fromJson(AirContent, Air.class);
                    mWeatherId = weather.basic.parent_city;

                    WeatherShow(weather.basic.parent_city,weather.update.updateTime,weather.now.tmp,weather.now.hum,weather.now.cond_txt,lifestyle1.txt,lifestyle2.txt,lifestyle3.txt);
                    AirShow(air.airNowCity.aqi,air.airNowCity.pm25,air.airNowCity.qlty);
                    //Log.d("WeatherActivity",air.airNowCity.aqi);

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
        swipeRefresh.setRefreshing(false);
        loadBingPic();
    }

    private void AirShow(final String Aqi, final String Pm25, final String Qlty) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView aqi = (TextView)findViewById(R.id.aqi_text);
                TextView pm25 = (TextView)findViewById(R.id.pm25_text);
                TextView qlt= (TextView)findViewById(R.id.qlt_text);
                aqi.setText(Aqi);
                pm25.setText(Pm25);
                qlt.setText(Qlty);
            }
        });
    }

    private void WeatherShow(final String cityName,final String nowTime,final String Tmp, final String Hum, final String Cond_txt, final String Txt1, final String Txt2, final String Txt3) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                TextView city_name = (TextView)findViewById(R.id.title_city);
                TextView now_time = (TextView)findViewById(R.id.title_update_time);
                TextView tmp = (TextView)findViewById(R.id.tmp_text);
                TextView hum = (TextView)findViewById(R.id.hum_text);
                TextView cond_txt= (TextView)findViewById(R.id.cond_text);
                TextView suggestion1= (TextView)findViewById(R.id.suggestion1);
                TextView suggestion2= (TextView)findViewById(R.id.suggestion2);
                TextView suggestion3= (TextView)findViewById(R.id.suggestion3);

                city_name.setText(cityName);
                now_time.setText(nowTime);
                tmp.setText(Tmp+"℃");
                hum.setText(Hum);
                cond_txt.setText(Cond_txt);
                suggestion1.setText("(1)"+Txt1);
                suggestion2.setText("(2)"+Txt2);
                suggestion3.setText("(3)"+Txt3);
            }
        });
    }
    private void showWeatherInfo(Weather weather,Air air) {
        Lifestyle lifestyle1 = weather.lifestyles.get(0);
        Lifestyle lifestyle2 = weather.lifestyles.get(1);
        Lifestyle lifestyle3 = weather.lifestyles.get(2);
        WeatherShow(weather.basic.parent_city,weather.update.updateTime,weather.now.tmp,weather.now.hum,weather.now.cond_txt,lifestyle1.txt,lifestyle2.txt,lifestyle3.txt);
        AirShow(air.airNowCity.aqi,air.airNowCity.pm25,air.airNowCity.qlty);

    }

}




