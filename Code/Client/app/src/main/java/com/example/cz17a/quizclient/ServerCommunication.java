package com.example.cz17a.quizclient;

import android.widget.ArrayAdapter;

import com.example.cz17a.quizclient.ClientThread.ClientThreadGET;

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
 * @version 0.2.1
 * @author Willy Steinbach, Thomas Gerbert
 */

public class ServerCommunication {

    final static private String URLROOT = "http://pcai042.informatik.uni-leipzig.de:1810/restserver/webapi";
    final static private String URLQUIZ = "/quizzes";
    private User usr = null;


    /**
     *  generates quest-url
     * @param id = QuizId
     * @return URL
     */
    public String createUrlQuestion(String id){
        String urlQuest = "/quizzes/" + id + "/random_questions";
        return urlQuest;
    }

    /**
     *  generates answer-url
     * @param id = AnswerId
     * @return URL
     */
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
            jType = new ClientThreadGET().execute(url).get();
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
     * @param id = quizId
     * @return list of questions from quiz as a JSON
     */
    public JSONArray getRandQuestionsJSON(String id) {
        JSONArray jType = null;
        try {
            URL url = new URL(URLROOT  + createUrlQuestion(id));
            jType = new ClientThreadGET().execute(url).get();
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
     * Method that sets up the Quizzes/Topics from the JSONArray
     * got from the Server
     * @param quizzes The object the quizzes should be added to
     */
    public void setUpQuizzes(Quizzes quizzes){
        JSONArray jsonArray = getQuizzesJSON();
        for(int i = 0; i<jsonArray.length();i++){
            quizzes.topics.add(new Topic());
            try {
                quizzes.topics.get(i).setTitle(jsonArray.getJSONObject(i).getString("title"));
                quizzes.topics.get(i).setId(jsonArray.getJSONObject(i).getInt("id"));
                quizzes.topics.get(i).setMaxParticipants(jsonArray.getJSONObject(i).getInt("maxParticipants"));
                quizzes.topics.get(i).setMinParticipants(jsonArray.getJSONObject(i).getInt("minParticipants"));
                quizzes.topics.get(i).setLength(jsonArray.getJSONObject(i).getInt("length"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Method that gets the Questions as a JSON from the Server
     * @param quizId Is the ID of the Quiz/Topic that is going to be played
     * @return an Array of Questions
     */
    public Question[] getQuestions(int quizId){
        JSONArray jsonArray = getRandQuestionsJSON(String.valueOf(quizId));
        int questionCount = jsonArray.length();
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

    public void createUsr(JSONObject usr){

    }
}

