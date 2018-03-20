package com.example.cz17a.quizclient.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;

import com.example.cz17a.quizclient.GameLogic;
import com.example.cz17a.quizclient.Src.Question;
import com.example.cz17a.quizclient.R;

import java.util.Arrays;

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
    GameLogic game= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //socketCommunication = new SocketCommunication(50000, URLHandler.SERVERROOT,null);
        quizId = getIntent().getExtras().getInt("quizId",1);
        gameId = Integer.parseInt(getIntent().getExtras().getString("gameId","1"));
        questionList = (Question[]) getIntent().getExtras().get("questionList");
        newGame();
    }

    /**
     * Method that generates a new Game, initializes all Buttons, Text Views
     * and the GameLogic with Questions and Answers
     */
    public void newGame() {
        final Button[] buttons = new Button[4];
        //defines the Buttons and TextViews
        buttons[0] = findViewById(R.id.antwort1but);
        buttons[1] = findViewById(R.id.antwort2but);
        buttons[2] = findViewById(R.id.antwort3but);
        buttons[3] = findViewById(R.id.antwort4but);

        indicator = findViewById(R.id.indicator);
        TextView timer = findViewById(R.id.timer);
        TextView questionText = findViewById(R.id.fragenText);
        TextView scoreView = findViewById(R.id.points);
        TextView jackpotView = findViewById(R.id.jackpot);
        questionText.setGravity(Gravity.CENTER);
        indicator.setGravity(Gravity.CENTER);
        final GameLogic game = new GameLogic(quizId, gameId, questionList, buttons, questionText, indicator, timer, scoreView, jackpotView);
        game.playNewQuestion();
    }

    public void triggerNewQuestion(){
        game.playNewQuestion();
    }
}








