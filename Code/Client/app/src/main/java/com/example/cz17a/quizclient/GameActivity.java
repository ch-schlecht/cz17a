package com.example.cz17a.quizclient;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        newGame();
    }

    /**
     * Methode zur Erzeugung eines neuen Spiels
     * Initialisierung aller Buttons, TextViews
     * Erzeugung der Spiellogik mit Fragen und Antworten
     */
    public void newGame() {
        final GameLogic game = new GameLogic();
        final Button[] buttons = new Button[4];
        buttons[0] = findViewById(R.id.antwort1but);
        buttons[1] = findViewById(R.id.antwort2but);
        buttons[2] = findViewById(R.id.antwort3but);
        buttons[3] = findViewById(R.id.antwort4but);
        final TextView indicator = findViewById(R.id.indicatior);
        final TextView timer = findViewById(R.id.timer);
        final TextView fragenText = findViewById(R.id.fragenText);
        fragenText.setGravity(Gravity.CENTER);
        indicator.setGravity(Gravity.CENTER);
        //playNewFrage(game.fragenkatalog[0],buttons, fragenText, indicator, timer);
        int delay = 15000;
        for (int i = 0; i < game.fragenanzahl; i++) {
            final int finalI = i;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < game.fragenanzahl; i++) {
                        game.playNewFrage(game.fragenkatalog[finalI], buttons, fragenText, indicator, timer);
                    }
                }
            }, delay * i);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        }, delay * (game.fragenanzahl + 1));
    }
}








