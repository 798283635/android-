package com.example.lutmanage.db;

import org.litepal.crud.LitePalSupport;

/**
 * Created by asus on 2019/8/1.
 */

public class Customer extends LitePalSupport {

    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
