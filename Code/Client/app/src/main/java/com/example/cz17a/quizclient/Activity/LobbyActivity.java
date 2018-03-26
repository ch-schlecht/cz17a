package com.example.cz17a.quizclient.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.cz17a.quizclient.ServerClient.ServerCommunication;
import com.example.cz17a.quizclient.ServerClient.SocketCommunication;
import com.example.cz17a.quizclient.ServerClient.SocketHandler;
import com.example.cz17a.quizclient.Src.Question;
import com.example.cz17a.quizclient.Src.Quizzes;
import com.example.cz17a.quizclient.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class LobbyActivity extends AppCompatActivity {
    public static Quizzes quizzes;
//    public static User user;
    int quizId;
    ArrayList<String> players; //List of Player Names
    ListView playerListView;
    ArrayAdapter<String> playerlistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SocketCommunication.lobby = this;
        super.onCreate(savedInstanceState);
        quizId = getIntent().getExtras().getInt("quizId",160); //TODO
        //players = new List<String>;
        setContentView(R.layout.activity_lobby);
        Button skipLobby = findViewById(R.id.skipButton);
        skipLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goToGame();
            }
        });
        Button leaveButton = findViewById(R.id.leaveButton);
        leaveButton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view){
               //leave Lobby
               ServerCommunication com = new ServerCommunication();
                com.usrLeaveLobby(""+quizId, SocketHandler.getUserId());
                onBackPressed();
               //goto TopicSelection
           }
        });

        /**
        Thread socketCom = new Thread(new SocketCommunication(port));
        socketCom.run();
         **/
        ServerCommunication com = new ServerCommunication();

        //Load UserID
        /**
        SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
        String usrId= sp.getString("uId","-1"); //TODO other Default
        System.out.println("Loaded User from SharedPref: "+usrId);
**/
        String usrId = SocketHandler.getUserId();
        com.usrJoinLobby(""+quizId, usrId, this);
    }
    public void goToGame(int gameId, Question[] questionList) {
        Intent intent = new Intent(this,  GameActivity.class);
        intent.putExtra("quizId", quizId);
        intent.putExtra("gameId",gameId);
        intent.putExtra("questionList",questionList);
        startActivity(intent);
    }

    public void goBack() {
        Intent intent = new Intent(this, TopicSelection.class);
        startActivity(intent);
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
        playerlistAdapter =
                new ArrayAdapter<String>(
                        this, // Die aktuelle Umgebung (diese Activity)
                        R.layout.list_player_item, // ID der XML-Layout Datei
                        //R.id.playerlist, // ID des TextViews
                        players); // Beispieldaten in einer ArrayList
        playerListView = findViewById(R.id.playerlist);
        playerListView.setAdapter(playerlistAdapter);
    }
}
