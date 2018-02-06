package com.example.cz17a.quizclient;

import java.sql.Timestamp;

/**
 * Created by Willy Steinbach on 06.02.2018.
 */

public class User {
    int id;
    String mail;
    String nickname;
    Timestamp lastLogin;
    Timestamp regristration;
    float playtime;

    public void setId(int id) {
        this.id = id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getNickname() {
        return nickname;
    }
}
