package com.example.cz17a.quizclient.ServerClient;

import com.example.cz17a.quizclient.Activity.LobbyActivity;
import com.example.cz17a.quizclient.Src.Question;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by felixfink on 19.02.18.
 */

public class SocketCommunication implements Runnable{


    Socket server = null;
    BufferedReader in = null;
    //char buffer[] = new char[1024];
    PrintWriter out = null;
    int port = 0;
    boolean running;
    String[] statusMessages;
    //int playernumberGameStartedWith;
    //String gameID;
    String ip;
    LobbyActivity lobby;
    Scanner scan = null;

    public SocketCommunication(int port, String ip, LobbyActivity lobby){

        this.port = port;
        this.ip = ip;
        running = true;
        this.lobby = lobby;

    }

    /**
     * Establish a connection to the Server Socket and initialize Input- and OutputStreams.
     */
   public void connect() {
       //try {
       try {
           //server = new ServerSocket(port);
           System.out.println("conn to "+InetAddress.getByName(URLHandler.SERVERROOT)+":"+port);
           server = new Socket(InetAddress.getByName(URLHandler.SERVERROOT),port); //socket.accept(); //wait for connection from server (in this case client)
           //in = new BufferedReader(new InputStreamReader(server.getInputStream()));
           scan = new Scanner(server.getInputStream());
           out = new PrintWriter(new OutputStreamWriter(server.getOutputStream()));
       } catch (IOException e) {
           e.printStackTrace();
       }


   }

    /**
     * Method that receives a Message from the Client Socket from Server and returns this String.
     * @return String
     */
   public String receivedMessagesFromServer(){
       String message = "";
       System.out.println("i am in receive");
       try {
           scan.useDelimiter(Pattern.quote("$"));
           while(scan.hasNext()){
               System.out.println("i am reading");
               message = scan.next();
               System.out.println(message);
               return message;
           }
       } catch (Exception e) {
          // e.printStackTrace();
       }
       return message;
   }

   public void sendMessageToServersocketAtServer(String message) throws IOException{
       out.println(message);
       out.flush();
   }



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
       try {
           server.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    @Override
    public void run() {
        System.out.println("Client Port:"+port+" inits connection");
        String gameId = "";
       connect();
        while(running){
            String msg = receivedMessagesFromServer();
            if(!((msg  == "") || (msg == null))){
                System.out.println(msg);
                //TODO identifier for player list
                msg =  msg.replace("{","");
                msg = msg.replace("}","");
                String[] players  = msg.split(",");

                //lobby.setPlayers(players);


                if(msg.matches("^[0-9{}]+$")){ //regex to match the gameID
                    System.out.println("GameID is: " + msg);
                    gameId = msg;
                }
                if(msg.startsWith("[{")){
                    lobby.goToGame(gameId);
                }


                //TODO Lobbyanzeige
            }

        }
    }
}

