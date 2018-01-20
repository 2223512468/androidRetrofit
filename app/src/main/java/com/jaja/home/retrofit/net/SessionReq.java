package com.jaja.home.retrofit.net;

/**
 * Created by ${Terry} on 2018/1/20.
 */
public class SessionReq {

    private int userId;
    private String token;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
