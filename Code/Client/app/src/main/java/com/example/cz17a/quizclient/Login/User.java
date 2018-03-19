package com.example.cz17a.quizclient.Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;


/**
 * Created by Willy Steinbach on 06.02.2018.
 */

public class User {
    private String id;
    private String mail;
    private String nickname;
    private Timestamp lastLogin;
    private Timestamp regristration;
    private float playtime;
    private String password;

    public User(String mail, String nickname){
        this.mail = mail;
        this.nickname = nickname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getNickname() {
        return nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JSONObject toJSON(){
        JSONObject usrJSON = new JSONObject();
        try {
            usrJSON.put("mail", this.mail);
            usrJSON.put("nickname", this.nickname);
            usrJSON.put("password",this.password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return usrJSON;
    }
}
