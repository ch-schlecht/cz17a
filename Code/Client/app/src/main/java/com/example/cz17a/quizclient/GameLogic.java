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
    int questioncount;
    Question questionlist[];
    ServerCommunication servCom;
    public GameLogic(){
        servCom = new ServerCommunication();
        questionlist = servCom.getQuestions();
        questioncount = questionlist.length;

    }
    public void playNewQuestion(final Question question, final Button[] buttons, TextView questionText, final TextView indicator, final TextView timer){
        indicator.setVisibility(View.INVISIBLE);
        for(int i = 0; i<4; i++){
            buttons[i].setText(question.getAnswers(i));
            final int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    evaluation(buttons, finalI , question, indicator);
                }
            });
        }
        questionText.setText(question.getQuestionText());
        buttonsActivate(buttons);
        new CountDownTimer(10000,1000){
            public void onTick(long millisUntilFinished){
                timer.setText("Zeit: " + millisUntilFinished/1000+ "s");
            }
            public void onFinish(){
                if(!question.isValuated){
                    System.out.println("Auswertung nach Timeout!");
                    evaluation(buttons, -1 , question, indicator);
                }

            }
        }.start();
    }

    public void evaluation(Button[] buttons, int i, Question frage, TextView indicator){
        buttonsDeactivate(buttons);
        //Antwort noch nicht validiert
        System.out.println("Auswertung. Validierung: Button: " + i);
        if(i < 0){
            indicator.setText("Zeit vorbei!");
        }else{
            System.out.println("Richtige Antwort: " + frage.getAnswers(0));
            if (buttons[i].getText().equals(frage.getAnswers(0))) {
                indicator.setText("RICHTIG!");
            }else {
                indicator.setText("FALSCH!");
            }
        }
        indicator.setVisibility(View.VISIBLE);
        frage.isValuated = true;



    }

    public void buttonsDeactivate(Button[] buttons){
        for (int i= 0; i<4;i++){
            buttons[i].setClickable(false);
        }
    }
    public void buttonsActivate(Button[] buttons){
        for (int i= 0; i<4;i++){
            buttons[i].setClickable(true);
        }
    }
}

