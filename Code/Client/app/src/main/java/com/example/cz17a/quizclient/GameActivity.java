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
    public void newGame(){
        final GameLogic game= new GameLogic();
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
        for(int i = 0; i< game.fragenanzahl; i++){
            final int finalI = i;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i<game.fragenanzahl; i++) {
                        playNewFrage(game.fragenkatalog[finalI], buttons, fragenText, indicator, timer);
                    }
                }
            }, delay * i);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        }, delay * (game.fragenanzahl+1));

    }

    /**
     * Erzeugung einer einzelnen Fragerunde
     * @param frage
     * @param buttons
     * @param fragenText
     * @param indicator
     */
    public void playNewFrage(final Frage frage, final Button[] buttons, TextView fragenText, final TextView indicator, final TextView timer){
        indicator.setVisibility(View.INVISIBLE);
        for(int i = 0; i<4; i++){
            buttons[i].setText(frage.getAntwort(i));
            final int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    auswertung(buttons, finalI , frage, indicator);
                }
            });
        }
        fragenText.setText(frage.getFragenText());
        buttonsAktivieren(buttons);
        new CountDownTimer(10000,1000){
            public void onTick(long millisUntilFinished){
                timer.setText("Zeit: " + millisUntilFinished/1000+ "s");
            }
            public void onFinish(){
                if(!frage.isValuated){
                    System.out.println("Auswertung nach Timeout!");
                    auswertung(buttons, -1 , frage, indicator);
                }

            }
        }.start();
    }





    public void auswertung(Button[] buttons, int i, Frage frage, TextView indicator){
        buttonsDeaktivieren(buttons);
        //Antwort noch nicht validiert
        System.out.println("Auswertung. Validierung: Button: " + i);
            if(i < 0){
                indicator.setText("Zeit vorbei!");
            }else{
                System.out.println("Richtige Antwort: " + frage.getAntwort(0));
                if (buttons[i].getText().equals(frage.getAntwort(0))) {
                    indicator.setText("RICHTIG!");
                }else {
                    indicator.setText("FALSCH!");
                }
            }






/*
            if(buttons[i].getText() == frage.getAntwort(0)&&i>=0){
                indicator.setText("RICHTIG!");
            }else if(i==-1){
                indicator.setText("Zeit vorbei!");
            }else{
                indicator.setText("FALSCH");
            }*/
        indicator.setVisibility(View.VISIBLE);
        frage.isValuated = true;



    }

    public void buttonsDeaktivieren(Button[] buttons){
        for (int i= 0; i<4;i++){
            buttons[i].setClickable(false);
        }
    }
    public void buttonsAktivieren(Button[] buttons){
        for (int i= 0; i<4;i++){
            buttons[i].setClickable(true);
        }
    }
}
