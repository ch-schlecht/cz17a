package com.example.cz17a.quizclient;

import android.widget.ArrayAdapter;

import org.json.JSONArray;
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
import java.util.concurrent.ExecutionException;

/**
 * Created by Willy Steinbach on 17.01.2018.
 * Edited by Thomas Gerbert on 18.01.2018
 * Edited by Thomas Gerbert/ Willy Steinbach on 29.01.2018
 */

public class ServerCommunication {

    final static private String URLROOT = "http://pcai042.informatik.uni-leipzig.de:1810/restserver/webapi";
    final static private String URLQUIZ = "/quizzes";
    private String nudes = null;

    JSONObject jType = null;

    public String createUrlQuestion(String id){
        String urlQuest = "/quizzes/" + id + "/random_questions";
        return urlQuest;
    }

    public String createUrlAnswer(String id){
        String urlAnswer = "/questions/"+ id + "/answers";

        return urlAnswer;
    }


    /**
     * used to pull topic/quiz-list
     * @return list of all quizzes as a JSON
     */
    public JSONArray getQuizzesJSON() {
        JSONArray jType = null;
        try {
            URL url = new URL(URLROOT  + URLQUIZ);
            jType = new ClientThread().execute(url).get();
            return jType;
        }catch(IOException e){

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

      return null;
    }
    /**
     * requests list of questions from topic
     * @param id of quiz
     * @return list of questions from quiz as a JSON
     */
    public JSONArray getRandQuestionsJSON(String id) {
        JSONArray jType = null;
        try {
            URL url = new URL(URLROOT  + createUrlQuestion(id));
            jType = new ClientThread().execute(url).get();
            return jType;
        }catch(IOException e){

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }



    // under construction
    public void sendResponse(){
        try{
            if(URLROOT != null && nudes != null){
                URL url = new URL(URLROOT + nudes);
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


    /**
     * Set topics of the quizzes
     * @param topicHandler The TopicHander to which the topics and ids are added
     * @return Returns the length of the Id array
     */
   public int setTopics(TopicHandler topicHandler){
        JSONArray jsonArray = getQuizzesJSON();
        topicHandler.titles = new String[jsonArray.length()];
        System.out.println("LENGTH: " + jsonArray.length());
        topicHandler.IDs = new int[jsonArray.length()];
        for(int i = 0; i<jsonArray.length();i++){
            try {
                topicHandler.titles[i] = jsonArray.getJSONObject(i).getString("title");
                topicHandler.IDs[i] = jsonArray.getJSONObject(i).getInt("id");
            } catch (JSONException e) {
                e.printStackTrace();
            }

       }
       return topicHandler.IDs.length;

    }



    public Question[] getQuestions(){
        JSONArray jsonArray = getRandQuestionsJSON("1");
        int questionCount = 2;
        Question[] questionArray = new Question[questionCount];
        for (int i = 0; i<questionCount; i++){
            questionArray[i] = new Question();
            try {
                questionArray[i].setQuestionText(jsonArray.getJSONObject(i).getString("questioning"));
                questionArray[i].setQuestionID(jsonArray.getJSONObject(i).getString("id"));
                String[] answers = new String[4];
                for(int f = 0; f<4;f++){
                    answers[f] = jsonArray.getJSONObject(i).getJSONArray("answers").getJSONObject(f).getString("content");
                }
                questionArray[i].setAnswers(answers);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return questionArray;
    }
}
