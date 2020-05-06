package com.example.gsontest.gsonWeather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus on 2019/8/10.
 */

public class Basic {

    public String parent_city;
    @SerializedName("cid")
    public String code;

    public String cnty;
}
