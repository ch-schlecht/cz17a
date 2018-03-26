package com.example.cz17a.quizclient.ServerClient;

import com.example.cz17a.quizclient.Activity.GameActivity;
import com.example.cz17a.quizclient.Activity.LobbyActivity;
import com.example.cz17a.quizclient.GameLogic;
import com.example.cz17a.quizclient.Src.Jackpot;
import com.example.cz17a.quizclient.Src.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;



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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by felixfink on 19.02.18.
 */

public class SocketCommunication implements Runnable {
    Socket server = null;
    BufferedReader in = null;
    int port = 0;
    boolean running;
    GameLogic game;
    String ip;
    public static LobbyActivity lobby;
    public static GameActivity gameActivity;

    public SocketCommunication(int port, String ip) {
        this.port = port;
        this.ip = ip;
    }

    public SocketCommunication(int port, String ip, LobbyActivity lobby) {
        this.port = port;
        this.ip = ip;
        running = true;
        this.lobby = lobby;
    }

    /**
     * Establish a connection to the Server Socket and initialize Input- and OutputStreams.
     */
   public void connect() {
       try {
           System.out.println("conn to "+InetAddress.getByName(URLHandler.SERVERROOT)+":"+port);
           server = new Socket(InetAddress.getByName(URLHandler.SERVERROOT),port); //socket.accept(); //wait for connection from server (in this case client)
           in = new BufferedReader(new InputStreamReader(server.getInputStream()));
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    /**
     * Method that receives a Message from the Client Socket from Server and returns this String.
     * @return String
     */
   public String receivedMessageFromServer(){
       String message = null;
       try {
           message = in.readLine();
       } catch (IOException e) {
           e.printStackTrace();
       }
       System.out.println("Received Message: " + message);
       return message;
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
       running = true;
        int gameId = -1; //default -1 to indicate failure
       connect();
        while(running){
            String msg = receivedMessageFromServer();
            if(msg != null) {
                //System.out.println(msg);
                final ArrayList<String> players = new ArrayList<>();
                if(msg.contains("lobby")) {
                    try {
                        JSONObject jsonObject = new JSONObject(msg);
                        JSONArray jsonArray = jsonObject.getJSONArray("lobby");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            String wholePlayers = jsonArray.getString(i);
                            System.out.println("Players in String: " + wholePlayers);
                            String[] playerSeparation = wholePlayers.split(",");
                            for(int j = 0; j < playerSeparation.length; j++) {
                                players.add(playerSeparation[j]);
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //TODO Lobbyanzeige
                    lobby.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lobby.setPlayers(players);
                        }
                    });
                }
                else if(msg.contains("gameId")){ //regex to match the gameID
                    System.out.println("message for GameID is: " + msg);
                    try {
                        JSONObject json = new JSONObject(msg);
                        gameId = json.getInt("gameId");
                        System.out.println("gameID is: " + gameId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else if(msg.contains("questions")){
                    System.out.println("Found Questions");
                    //TODO get questions
                    //Question[] questionList = null;
                    Question[] questionArray = null;//list of questions init
                    try {
                        JSONObject json = new JSONObject(msg);
                        JSONArray jsonList = json.getJSONArray("questions");
                        int count = jsonList.length();
                        questionArray = new Question[count];
                        for(int i = 0; i < jsonList.length(); i++){
                            questionArray[i] = new Question();
                            try {
                                questionArray[i].jsonToQuestion(jsonList.getJSONObject(i)); //questlist i = inc array i
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        //questionList = new Question[jsonList.length()];
                        //for(int i = 0; i < jsonList.length(); i++){
                            //Mapping
                            //questionList[i] = (Question) jsonList.get(i);
                            //System.out.println("Mapped Question: " + questionList[i].toString());
                        //}

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    lobby.goToGame(gameId,questionArray);
                }
                else if(msg.contains("jackpot")) {
                    try {
                        JSONObject pureJSON = new JSONObject(msg); //this is the original JSON
                        JSONObject json = pureJSON.getJSONObject("jackpot"); //this is the JSON behind the "jackpot" key
                        Jackpot jackpot =  gameActivity.getGame().getJackpot();
                        jackpot.setAmount(json.getInt("amount"));
                        jackpot.setActive(json.getBoolean("active"));
                        gameActivity.triggerNewQuestion();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(game != null){
                    GameLogic game = gameActivity.getGame();
                    if(game.getCurrentQuestionIndex() == game.getQuestions().length) {
                        gameActivity.goToScoreboard(msg);
                    }
                }
            }
        }
    }
}

