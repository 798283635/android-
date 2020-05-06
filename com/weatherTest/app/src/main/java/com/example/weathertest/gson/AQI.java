package com.example.weathertest.gson;

/**
 * Created by asus on 2019/8/10.
 */

public class AQI {
    public  AQICity city;
    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
