package com.example.cz17a.quizclient.ServerClient;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by thomas on 19.02.2018.
 */

public class URLHandler {

    private URL url = null;
    final static private String URLROOT = "http://pcai042.informatik.uni-leipzig.de:1810/restserver/webapi";
    final static private String URLQUIZ = "/quizzes";

    public static String getURLROOT() {
        return URLROOT;
    }

    public static String getURLQUIZ() {
        return URLQUIZ;
    }

    public URL genUsrUrl(String usrname, String pw, String email){
        try {
            url = new URL(URLROOT + "/users/register/" + usrname + "/" + pw + "/" + email);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     *
     * @param usrname
     * @param pw
     * @return null if failed
     */
    public URL genUsrLogInURL(String usrname, String pw){
        try {
            url = new URL(URLROOT + "/users/login/" + usrname + "/" + pw);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public URL genUsrLogOutURL(String usrname){
        try {
            url = new URL(URLROOT + "/users/logout/" + usrname);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public URL genUsrForgotURL(String usrname){
        try {
            url = new URL(URLROOT + "/users/forgotPassword/" + usrname);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public URL lobbyURL(String quizId, String usrId, String port){
        try {
            url = new URL(URLROOT + "/users/"+quizId+"/join/"+usrId+"/"+port);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public URL genUsrRequestURL(String usrname){
        try {
            url = new URL(URLROOT + "/users/" + usrname);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }




}