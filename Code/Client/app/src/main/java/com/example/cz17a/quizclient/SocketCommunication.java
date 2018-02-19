package com.example.cz17a.quizclient;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by felixfink on 19.02.18.
 */

public class SocketCommunication {
// willy fk y
   public void connectionToServer() {
       Socket server = null;
       try {
           server = new Socket("serverIP", 500);
           BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
           PrintWriter out = new PrintWriter(server.getOutputStream(), true);


       } catch (UnknownHostException uhe) {
           uhe.printStackTrace();

       } catch (IOException ioe) {
           ioe.printStackTrace();

       } finally {
           if (server != null) {
               try {
                   server.close();
               } catch (IOException ioe) {
                   ioe.printStackTrace();
               }
           }
       }
   }

   public void sendAnswer(String answer){


   }
   public Question getNextQuestion(){

        Question question = new Question();
        question.dummyQuestion();
        System.out.println("DummyFrage: "+ question.toString());
       return question;
   }

}

