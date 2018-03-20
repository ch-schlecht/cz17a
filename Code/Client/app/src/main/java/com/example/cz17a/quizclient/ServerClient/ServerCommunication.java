package com.example.cz17a.quizclient.ServerClient;

import com.example.cz17a.quizclient.Activity.LobbyActivity;
import com.example.cz17a.quizclient.ServerClient.ClientThread.ClientThreadGETArray;
import com.example.cz17a.quizclient.ServerClient.ClientThread.ClientThreadGETObject;
import com.example.cz17a.quizclient.ServerClient.ClientThread.ClientThreadGETString;
import com.example.cz17a.quizclient.ServerClient.ClientThread.ClientThreadPOST;
import com.example.cz17a.quizclient.Src.Question;
import com.example.cz17a.quizclient.Src.Quizzes;
import com.example.cz17a.quizclient.Src.Quiz;
import com.example.cz17a.quizclient.Login.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * @version 0.2.1
 * @author Willy Steinbach, Thomas Gerbert
 */

public class ServerCommunication {

    /**
     *  generates quest-url
     * @param id = QuizId
     * @return URL
     */
    public static String createUrlQuestion(String id){
        String urlQuest = "/quizzes/" + id + "/random_questions";
        return urlQuest;
    }

    /**
     *  generates answer-url
     * @param id = AnswerId
     * @return URL
     * @deprecated
     */
    public static String createUrlAnswer(String id){
        String urlAnswer = "/questions/"+ id + "/answers";
        return urlAnswer;
    }


    /**
     * used to pull topic/quiz-list
     * @return list of all quizzes as a JSON
     */
    public static JSONArray getQuizzesJSON() {
        JSONArray jType = null;
        try {
            URL url = new URL(URLHandler.getURLROOT()  + URLHandler.getURLQUIZ());
            jType = new ClientThreadGETArray().execute(url).get();
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
    public static JSONArray getRandQuestionsJSON(String id) {
        JSONArray jType = null;
        try {
            URL url = new URL(URLHandler.getURLROOT()  + createUrlQuestion(id));
            jType = new ClientThreadGETArray().execute(url).get();
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
    public static void setUpQuizzes(Quizzes quizzes){
        JSONArray jsonArray = null;
        jsonArray =getQuizzesJSON();
        for(int i = 0; i<jsonArray.length();i++){
            quizzes.getTopics().add(new Quiz());
            try {
                quizzes.getTopics().get(i).setTitle(jsonArray.getJSONObject(i).getString("title"));
                quizzes.getTopics().get(i).setId(jsonArray.getJSONObject(i).getInt("id"));
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
     * @deprecated
     */

    public static Question[] getQuestions(int quizId){
        JSONArray jsonArray = null;
        jsonArray = getRandQuestionsJSON(String.valueOf(quizId));
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

    /**
     *
     * @param usr
     * @return usr stats
     */

    public static User getUser(User usr){
        JSONObject jUsr = null;
        try {
            URL url = URLHandler.genUsrRequestURL(usr.getNickname());
            jUsr = new ClientThreadGETObject().execute(url).get();
            return usr;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //usr stats
        try {
            usr.setId(jUsr.getString("id"));
            usr.setMail(jUsr.getString("mail"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return usr;
    }

    /**
     *
     * @return true by success
     */
    public static String usrRegistry(User user){   //players
        URL url = null;
        boolean success = false;
        String result = null;
        try {
            url = URLHandler.genUsrUrl();
           result = new ClientThreadPOST(user.toJSON()).execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param usrname
     * @param pw
     * @return true by success
     */
    public static int usrLogin(String usrname, String pw, String email){
        //email = "steinbach_willy@outlook.de";
        URL url = null;
        String recieve = "";
        JSONObject usrlog = new JSONObject();
        try {
            usrlog.put("nickname",usrname);
            usrlog.put("password", pw);
            usrlog.put("mail", email);
        } catch (JSONException e) {
            System.err.println("Data is no JSON");
            e.printStackTrace();
        }
        System.out.println("LOGIN:" + usrlog);
        try {
            url = URLHandler.genUsrLogInURL();
            recieve =  ""+new ClientThreadPOST(usrlog).execute(url).get(); //PostRequest.doPostRequest(url.toString(),usrlog.toString());
            //success = new ClientThreadPOST(usrlog).execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("recieved: "+recieve);
        return Integer.parseInt(recieve);
    }

    /**
     *
     * @param usrname
     * @return true by success
     */
    public static boolean userLogout(String usrname){
        URL url = null;
        boolean success = false;
        try {
            url = URLHandler.genUsrLogOutURL(usrname);
             new ClientThreadPOST().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *
     * @param usrname
     * @return true by success
     */
    public static boolean userForgotPW(String usrname) {
        URL url = null;
        boolean success = false;
        try {
            url = URLHandler.genUsrForgotURL(usrname);
            new ClientThreadPOST().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return true;

    }

    /**
     *
     * @return true by success
     */
    public static boolean usrJoinLobby(String quizId, String usrId, LobbyActivity lobby) {
        URL url = null;
        boolean success = false;
        int port = 0;

        try {
            url = URLHandler.lobbyURL(quizId, usrId);


            System.out.println("URL:"+url);
            port = Integer.parseInt(new ClientThreadGETString().execute(url).get());


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        SocketCommunication com = new SocketCommunication(port,URLHandler.SERVERROOT,lobby);
        SocketHandler.setSocket(com); //setting SocketCommunication into Holder
        new Thread(com).start();

        return success;

    }


    public  static boolean usrLeaveLobby(String quizId,String usrId){
        boolean success = false;
        URL url = null;

        url = URLHandler.leaveLobbyURL(quizId,usrId);

        System.out.println("Leave: "+url);

        try {
            new ClientThreadGETString().execute(url).get(); //TODO
            System.out.println("leave lobby -> sucess");
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //TODO

        SocketHandler.getSocket().stop();
        SocketHandler.setSocket(null);

        return success;
    }

    public static boolean postPlayedQuestion(int gameId, int questionId, JSONObject json) {
        boolean success = false;
       URL url = URLHandler.genPlayedQuestionURL(gameId, questionId, Integer.parseInt(SocketHandler.getUserId()));
       String result = null;
       System.out.println("SEND PLAYED QUESTION: " + json.toString());
        try {
            result = new ClientThreadPOST(json).execute(url).get();
            success = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return success;
    }
}

