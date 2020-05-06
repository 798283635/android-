package com.example.gsontest.gsonWeather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus on 2019/8/10.
 */

public class Forecast {
    @SerializedName("tmp_max")
    public String MaxT;
    @SerializedName("tmp_min")
    public String MinT;

    public String cond_txt_d;
    public String date;


}
