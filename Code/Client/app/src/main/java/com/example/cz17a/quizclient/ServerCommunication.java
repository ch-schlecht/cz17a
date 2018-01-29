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

    final private String url_root = "http://pcai042.informatik.uni-leipzig.de:1810/restserver/webapi";
    final private String url_quiz = "/quizzes";
    private String nudes = null;

    public String create_url_question(String id){
        String url_quest = "/quizzes/" + id + "/random_questions";
        return url_quest;
    }

    public String create_url_answer(String id){
        String url_answer = "/questions/"+ id + "/answers";

        return url_answer;
    }

    public JSONObject get_quizzes_JSON() {
        JSONObject j_type = null;
      try {
          URL url = new URL(url_root + url_quiz);
          j_type = ask(url);
      }catch(IOException e){

      }

      return null;
    }



    public JSONObject getRandQuestionJSON(String id) {
        String url_quest = create_url_question(id);
        JSONObject j_type = null;
        try {
            URL url = new URL(url_root + url_quest);
            j_type = ask(url);
        }catch(IOException e){

        }

        return null;
    }



    public JSONObject get_answers_JSON(String id) {
        String url_answer = create_url_answer(id);
        JSONObject jType = null;
        try {
            URL url = new URL(url_root + url_answer);
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
    //Fügt Fragen zum Fragenkatalog hinzu, wandelt JSON in Frage
    public Question[] getQuestions(){
        JSONObject jsonObject = getRandQuestionJSON("0");
        //dummy für Fragenanzahl, Anzahl soll aus JsonObj erzeugt werden
        int questionCount = 3;
        //Array für die Frage
        Question[] questionArray = new Question[questionCount];

        //Dummy um die Fragen zu erzeugen -> Replace with JSON import
        //setFrageText und SetQuestionID

        for (int i = 0; i<questionCount; i++){
            questionArray[i] = new Question();
            questionArray[i].setQuestionText("Test Frage " + i);
            questionArray[i].setQuestionID(i);
        }
        getAnswers(questionArray);
        return questionArray;
    }
    public void getAnswers(Question[] questionArray){
        for(int i= 0;i<questionArray.length; i++) {
            //ID Der Frage mit questionArray[i].getID();
            //Damit dann JSONObj anfordern und die Antworten der Frage damit füllen

            //dummy -> Replace with JSON import
            String[] answers = new String[4];
            for (int f = 0; f < 4; f++) {
                answers[f] = "TestAntwort " + f;
            }
        }
    }


}
