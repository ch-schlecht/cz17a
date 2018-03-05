package com.example.cz17a.quizclient.ServerClient;

import com.example.cz17a.quizclient.Src.Question;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by felixfink on 19.02.18.
 */

public class SocketCommunication implements Runnable{

    //ServerSocket socket = null;
    Socket server = null;

    BufferedReader in = null;
    char buffer[] = new char[1024];
    PrintWriter out = null;
    int port = 0;
    boolean running;
    String[] statusMessages;
    int playernumberGameStartedWith;
    String gameID;

    public SocketCommunication(int port){

        this.port = port;

        running = true;
        connect();/*
        */
    }

    /**
     * Establish a connection to the Server Socket and initialize Input- and OutputStreams.
     */
   public void connect() {
       try {
           //socket = new ServerSocket(port);
           server = new Socket("IP", port); //socket.accept(); //wait for connection from server (in this case client)
           in = new BufferedReader(new InputStreamReader(server.getInputStream()));
           out = new PrintWriter(server.getOutputStream(), true);


       } catch (UnknownHostException uhe) {
           uhe.printStackTrace();

       } catch (IOException ioe) {
           ioe.printStackTrace();

       }
   }

    /**
     * Method that receives all Messages from Server Socket and saves them in the String Array.
     */
   public void receiveMessagesFromServer(){
        int i=0;
        while (true){
            try {
                System.out.println(in.readLine());
                if (in.readLine() == null){
                    continue;
                }
                else {
                    statusMessages[i] = in.readLine();
                    i++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   }

    /**
     * Tests if there are enough Player for a Game.
     *
     * @return boolean if there are enough People to Play a Round in this Lobby.
     */
   public boolean enoughPlayerToPlay(int minNumberofPlayer){

           int numberOfPeopleInLobby = 0;
           for(String message: statusMessages){
               if (message.startsWith("player:") && message != null){
                   numberOfPeopleInLobby++;
                   if (numberOfPeopleInLobby >= minNumberofPlayer){
                       playernumberGameStartedWith = numberOfPeopleInLobby;
                       return true;

                   }else continue;

               } else if (message == null){
                   return false;
               } else continue;
           }
           return false;

   }

    /**
     * Gets the gameID out of the status messages.
     */
   void gameID(){
       gameID = statusMessages[playernumberGameStartedWith-1];
   }

    /**
     *
     * @return gameID from the started Game.
     */
   public String getGameID(){
       return gameID;
   }

  /* public void disconnect() throws IOException {
       if(socket != null && !socket.isClosed()){
           socket.close();
       }
   }*/


   public void sendAnswer(String answer){

       out.write(answer);

   }


   public Question getNextQuestion(){

       Question question = new Question();
        /*
       try{
           int anz = in.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
           String data = new String(buffer, 0, anz);
           JSONObject object = new JSONObject(data);

           question = new Question();
           question.jsonToQuestion(object);


       }catch(IOException e){
           e.printStackTrace();
       }catch(JSONException e){
           e.printStackTrace();
       }
       */



        question.dummyQuestion();
        System.out.println("DummyFrage: "+ question.toString());

       return question;
   }


   public synchronized void stop(){
       running = false;
   }

    @Override
    public void run() {
        while(running){

        }
    }
}

