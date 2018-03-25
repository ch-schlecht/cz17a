package com.example.cz17a.quizclient.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;

import com.example.cz17a.quizclient.GameLogic;
import com.example.cz17a.quizclient.ServerClient.SocketCommunication;
import com.example.cz17a.quizclient.Src.Question;
import com.example.cz17a.quizclient.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Willy Steinbach
 */

public class GameActivity extends AppCompatActivity {
    int quizId;
    int gameId;
    Question[] questionList;
    TextView indicator = null;
    TextView timer = null;
    TextView questionText = null;
    TextView scoreView = null;
    TextView jackpotView;
    GameLogic game = null;
    final Button[] buttons = new Button[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Game Activity startup");
        SocketCommunication.gameActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //socketCommunication = new SocketCommunication(50000, URLHandler.SERVERROOT,null);
        quizId = getIntent().getExtras().getInt("quizId",1);
        gameId = getIntent().getExtras().getInt("gameId",1);
        questionList = (Question[]) getIntent().getExtras().get("questionList");
        buttons[0] = findViewById(R.id.antwort1but);
        buttons[1] = findViewById(R.id.antwort2but);
        buttons[2] = findViewById(R.id.antwort3but);
        buttons[3] = findViewById(R.id.antwort4but);
        indicator = findViewById(R.id.indicator);
        TextView timer = findViewById(R.id.timer);
        TextView questionText = findViewById(R.id.fragenText);
        scoreView = findViewById(R.id.points);
        jackpotView = findViewById(R.id.jackpot);
        game = new GameLogic(quizId, gameId, questionList, buttons, questionText, indicator, timer, scoreView, jackpotView);
        newGame();
    }

    public GameLogic getGame() {
        return game;
    }

    /**
     * Method that generates a new Game, initializes all Buttons, Text Views
     * and the GameLogic with Questions and Answers
     */
    public void newGame() {

        questionText.setGravity(Gravity.CENTER);
        indicator.setGravity(Gravity.CENTER);

        game.playNewQuestion();
    }

    public void goToScoreboard(String endResult) {
        Intent intent = new Intent(this,  ScoreboardActivity.class);
        intent.putExtra("endResult", endResult);
        startActivity(intent);
    }

    public void triggerNewQuestion(){
        game.playNewQuestion();
    }
}








