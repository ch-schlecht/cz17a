package com.example.cz17a.quizclient;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Willy Steinbach
 */

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        newGame();
    }

    /**
     * Method that generates a new Game, initializes all Buttons, Text Views
     * and the GameLogic with Questions and Answers
     */
    public void newGame() {
        final GameLogic game = new GameLogic();
        final Button[] buttons = new Button[4];
        //defines the Buttons and TextViews
        buttons[0] = findViewById(R.id.antwort1but);
        buttons[1] = findViewById(R.id.antwort2but);
        buttons[2] = findViewById(R.id.antwort3but);
        buttons[3] = findViewById(R.id.antwort4but);
        final TextView indicator = findViewById(R.id.indicatior);
        final TextView timer = findViewById(R.id.timer);
        final TextView questionText = findViewById(R.id.fragenText);
        questionText.setGravity(Gravity.CENTER);
        indicator.setGravity(Gravity.CENTER);
        //time for that a question is shown
        int delay = 15000;
        //starts every Question after a delay
        for (int i = 0; i < game.questioncount; i++) {
            final int finalI = i;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < game.questioncount; i++) {
                        System.out.println("PLAY QUESTION: "+ game.questionlist[0].getQuestionText());
                        game.playNewQuestion(game.questionlist[finalI], buttons, questionText, indicator, timer);
                    }
                }
            }, delay * i);
        }
        //after all questions are played return to previous activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        }, delay * (game.questioncount + 1));
    }
}








