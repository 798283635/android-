package com.example.asus.helloworld;

/**
 * Created by asus on 2019/7/30.
 */

public class Fruit {

    private String name;

    private int imageId;

    public Fruit(String name,int  imageId){
        this.name = name;
        this.imageId = imageId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
