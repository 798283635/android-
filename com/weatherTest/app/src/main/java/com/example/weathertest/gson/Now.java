package com.example.weathertest.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus on 2019/8/10.
 */

public class Now {

    @SerializedName("tmp")
    public String temperature;

    //天气状况
    @SerializedName("cond")
    public More more;

    public class More{

        @SerializedName("txt")
        public String info;
    }
}
