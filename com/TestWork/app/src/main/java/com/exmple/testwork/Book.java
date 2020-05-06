package com.exmple.testwork;

/**
 * Created by asus on 2019/9/6.
 */

public class Book  {

    private String name;
    private int imageId;
    public Book(String name, int imageId) {
        this.name = name;
        this.imageId = imageId; }
    public String getName() {
        return name; }
    public int getImageId() {
        return imageId; }
}
