package com.example.cz17a.quizclient.ServerClient;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by thomas on 19.02.2018.
 */

public class URLHandler {

    private URL url = null;
    final static public String SERVERROOT = /**"192.168.178.21";**/ "pcai042.informatik.uni-leipzig.de";
    final static private String URLROOT = "http://"+SERVERROOT+":1810/restserver/webapi"; //TODO 1810
    final static private String URLQUIZ = "/quizzes";

    public static String getURLROOT() {
        return URLROOT;
    }

    public static String getURLQUIZ() {
        return URLQUIZ;
    }

    public URL genUsrUrl(){
        try {
            url = new URL(URLROOT + "/players/register");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     *
     * @return null if failed
     */
    public URL genUsrLogInURL(){
        try {
            url = new URL(URLROOT + "/players/login");

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

    public URL lobbyURL(String quizId, String usrId){
        try {
            url = new URL(URLROOT +"/Lobbies/" + quizId+"/join/"+usrId);

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
