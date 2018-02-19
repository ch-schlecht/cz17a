package com.example.cz17a.quizclient.ServerClient;

import android.os.AsyncTask;

import com.example.cz17a.quizclient.ServerClient.ClientThread.ClientThreadPOST;
import com.example.cz17a.quizclient.Src.Question;
import com.example.cz17a.quizclient.Src.Quizzes;
import com.example.cz17a.quizclient.ServerClient.ClientThread.ClientThreadGET;
import com.example.cz17a.quizclient.Src.Topic;
import com.example.cz17a.quizclient.Login.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * @version 0.2.1
 * @author Willy Steinbach, Thomas Gerbert
 */

public class ServerCommunication {

    URLHandler urlHandler = new URLHandler();

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
            URL url = new URL(URLHandler.getURLROOT()  + URLHandler.getURLQUIZ());
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
            URL url = new URL(URLHandler.getURLROOT()  + createUrlQuestion(id));
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
            quizzes.getTopics().add(new Topic());
            try {
                quizzes.getTopics().get(i).setTitle(jsonArray.getJSONObject(i).getString("title"));
                quizzes.getTopics().get(i).setId(jsonArray.getJSONObject(i).getInt("id"));
                quizzes.getTopics().get(i).setMaxParticipants(jsonArray.getJSONObject(i).getInt("maxParticipants"));
                quizzes.getTopics().get(i).setMinParticipants(jsonArray.getJSONObject(i).getInt("minParticipants"));
                quizzes.getTopics().get(i).setLength(jsonArray.getJSONObject(i).getInt("length"));
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
                questionArray[i].jsonToQuestion(jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return questionArray;
    }

    public boolean usrRegistry(String usrname, String pw, String email){
        URL url = null;
        boolean success = false;
        try {
            url = urlHandler.genUsrUrl(usrname, pw, email);
           success = new ClientThreadPOST().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    return success;
    }

    public boolean usrLogin(String usrname, String pw){
        URL url = null;
        boolean success = false;
        try {
            url = urlHandler.genUsrLogInURL(usrname, pw);
            success = new ClientThreadPOST().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean userLogout(String usrname){
        URL url = null;
        boolean success = false;
        try {
            url = urlHandler.genUsrLogOutURL(usrname);
            success = new ClientThreadPOST().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean userForgotPW(String usrname) {
        URL url = null;
        boolean success = false;
        try {
            url = urlHandler.genUsrForgotURL(usrname);
            success = new ClientThreadPOST().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return success;

    }
}

