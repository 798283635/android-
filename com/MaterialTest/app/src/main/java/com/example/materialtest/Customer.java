package com.example.materialtest;

import org.litepal.crud.DataSupport;

/**
 * Created by asus on 2019/8/1.
 */

public class Customer extends DataSupport{

    private String username;
    private String password;
    private String address;
    private String phone;

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
