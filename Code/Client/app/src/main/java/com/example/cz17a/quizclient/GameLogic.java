package com.example.cz17a.quizclient;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by stein on 11.01.2018.
 */

public class GameLogic {
    int id;
    int fragenanzahl;
    Frage fragenkatalog[];
    public GameLogic(){
        fragenanzahl = 5;
        fragenkatalog = new Frage[fragenanzahl];
        for (int i = 0; i<fragenanzahl; i++){
            fragenkatalog[i] = new Frage();
            fragenkatalog[i].frage = "Test Frage " + i;
        }
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

