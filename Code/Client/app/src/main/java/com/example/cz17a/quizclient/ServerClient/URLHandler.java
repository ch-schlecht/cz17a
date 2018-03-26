package com.example.cz17a.quizclient.ServerClient;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by thomas on 19.02.2018.
 */

public class URLHandler {

    final static public String SERVERROOT =  /*"192.168.178.21";*/ "pcai042.informatik.uni-leipzig.de";
    final static private String URLROOT = "http://"+SERVERROOT+":1810/restserver/webapi"; //TODO 1810
    final static private String URLQUIZ = "/quizzes";

    public static String getURLROOT() {
        return URLROOT;
    }

    public static String getURLQUIZ() {
        return URLQUIZ;
    }

    public static URL genUsrUrl(){
        try {
            return new URL(URLROOT + "/players/register");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @return null if failed
     */
    public static URL genUsrLogInURL(){
        try {
            return new URL(URLROOT + "/players/login");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static URL genUsrLogOutURL(String usrname){
        try {
            return  new URL(URLROOT + "/users/logout/" + usrname);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static URL genUsrForgotURL(String usrname){
        try {
            return new URL(URLROOT + "/users/forgotPassword/" + usrname);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static URL lobbyURL(String quizId, String usrId){
        try {
            return new URL(URLROOT +"/lobbies/" + quizId+"/join/"+usrId);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static URL leaveLobbyURL(String quizId, String usrId){
        try {
            return new URL(URLROOT+"/lobbies/"+quizId+"/leave/"+usrId);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static URL genUsrRequestURL(String usrname){
        try {
            return new URL(URLROOT + "/users/" + usrname);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static URL genPlayedQuestionURL(int gameId, int questionId, int playerId) {
        try {
            return new URL(URLROOT + String.format("/questions/played/%d/%d/%d", gameId, questionId, playerId));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
