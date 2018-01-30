package com.example.cz17a.quizclient;

import java.util.ArrayList;

/**
 * Created by Willy Steinbach on 30.01.2018.
 */

/**
 * Representation of all quiztypes and topics
 */
public class Quizzes {
    ArrayList<Topic> topics = new ArrayList<>();
    public void Quizzes(){
    }

    /**
     * Defines the Quizzes by getting informations from the Server
     */
    public void setUpQuizzes(){
        ServerCommunication serverCom = new ServerCommunication();
        serverCom.setUpQuizzes(this);
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
