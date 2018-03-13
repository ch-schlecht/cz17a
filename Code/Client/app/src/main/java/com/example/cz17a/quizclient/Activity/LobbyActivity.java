package com.example.cz17a.quizclient.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.cz17a.quizclient.Login.User;
import com.example.cz17a.quizclient.ServerClient.ServerCommunication;
import com.example.cz17a.quizclient.ServerClient.SocketCommunication;
import com.example.cz17a.quizclient.Src.Quizzes;
import com.example.cz17a.quizclient.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LobbyActivity extends AppCompatActivity {
    public static Quizzes quizzes;
    public static User user;
    int quizId;


    List<String> players; //List of Player Names


    ListView playerListView;
    ArrayAdapter<String> playerlistAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quizId = getIntent().getExtras().getInt("quizId",160); //TODO
        //players = new List<String>;
        setContentView(R.layout.activity_lobby);
        Button skipLogin = findViewById(R.id.skipButton);
        skipLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGame();
            }
        });


        /**
        Thread socketCom = new Thread(new SocketCommunication(port));
        socketCom.run();

         **/
        ServerCommunication com = new ServerCommunication();

        //Load UserID
        SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
        String usrId= sp.getString("uId","160"); //TODO other Default
        System.out.println("Loaded User from SharedPref: "+usrId);

        com.usrJoinLobby(""+quizId, usrId, this);


    }
    public void goToGame(){
        Intent intent = new Intent(this,  GameActivity.class);
        GameActivity.quizzes = quizzes;
        intent.putExtra("quizId", quizId);
        startActivity(intent);
    }

    public void setPlayers(String[] players){
        this.players = Arrays.asList(players);
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
