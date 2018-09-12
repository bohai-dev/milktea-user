package com.milktea.milkteauser.domain;

import java.util.Date;

public class TeaGlobalToken {
    private String token;

    private Date insertTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}