package com.example.cz17a.quizclient;

import android.widget.ArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by stein on 17.01.2018.
 * Edited by gerbert on 18.01.2018
 */

public class ServerCommunication {

       final private String url_root = "http://pcai042.informatik.uni-leipzig.de:1810/restserver/";
       final private String url_quiz = null;
       final private String url_quest = null;
       final private String url_answer = null;
       private String nudes = null;


    public JSONObject getQuizTypeJSON() {
        JSONObject jType = null;
      try {
          URL url = new URL(url_root + url_quiz);
          jType = ask(url);
      }catch(IOException e){

      }

      return null;
    }



    public JSONObject getQuestionsJSON(int id) {
        JSONObject jType = null;
        try {
            URL url = new URL(url_root + url_quest + "/" + id);
            jType = ask(url);
        }catch(IOException e){

        }

        return null;
    }



    public JSONObject getAnswersJSON(int id) {
        JSONObject jType = null;
        try {
            URL url = new URL(url_root + url_answer + "/" + id);
            jType = ask(url);
        }catch(IOException e){

        }

        return null;
    }


    // under construction
    public void sendNudes(){
        try{
            if(url_root != null && nudes != null){
                URL url = new URL(url_root + nudes);
                HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                connect.setRequestMethod("POST"); //throws ProtocolException
                connect.connect();
    //            BufferedOutputStream usr_answer = new OutputStream(usr.getanswer()); //antwort des Users
                connect.disconnect();
            }else{
                System.out.println("URL nicht gesetzt");
                return;
            }
        }catch(IOException e){

        }
    }



    public JSONObject ask(URL url){
        String quizType = null;
        try {
             HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET"); //throws ProtocolException
            connect.connect();

            if (connect.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code: " + connect.getResponseCode());
            }
            BufferedReader cIn = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            while (cIn.readLine() != null) {
                quizType += cIn;
            }
            connect.disconnect();
        } catch (IOException e) {
            System.out.println("cant connect to given URL");
            return null;
        }
        JSONObject jType = null;
        try {
            jType = new JSONObject(quizType);
        } catch (JSONException j) {
            System.out.println("invalid Data");
        }
        return jType;
    }



    //dummi
   public String[] getTopicTitles(){
        String[] titles = new String[3];
        titles[0] = "Topic 0";
        titles[1] = "Topic 1";
        titles[2] = "Topic 2";
        return titles;

    }
    public int[] getTopicIDs(){
        int[] IDs = new int[3];
        for (int i = 0; i<3;i++){
            IDs[i] = i;
        }
        return IDs;
    }

    //todo getQuiz, getQuest, getAnswer?, giveAnswer



}
