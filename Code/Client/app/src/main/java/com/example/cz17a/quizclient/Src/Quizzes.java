package com.example.cz17a.quizclient.Src;

import com.example.cz17a.quizclient.ServerClient.ServerCommunication;

import java.util.ArrayList;

/**
 * Created by Willy Steinbach on 30.01.2018.
 */

/**
 * Representation of all quiztypes and topics
 */
public class Quizzes {
    private ArrayList<Topic> topics = new ArrayList<>();
    private ServerCommunication servCom;

    public ArrayList<Topic> getTopics() {
        return topics;
    }

    public Quizzes(ServerCommunication serverCommunication){
        servCom = serverCommunication;
    }

    /**
     * Defines the Quizzes by getting informations from the Server
     */
    public void setUpQuizzes(){
        servCom.setUpQuizzes(this);
    }

    /**
     * toString Method
     * @return String representation of all Quizzes
     */
    public String toString(){
        String retString = "";
        for(int i = 0;i<topics.size();i++){
            retString += topics.get(i).toString();
        }
        return retString;
    }
}
