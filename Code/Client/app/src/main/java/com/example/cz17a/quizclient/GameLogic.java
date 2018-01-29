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

    /**
     * Method for playing a specified Question
     * Modifies Buttons and TextFields of the GameActivity
     * @param question The question that is going to be played
     * @param buttons The answer buttons of the GameActivty
     * @param questionText The TextView, which is showing the question text
     * @param indicator The TextView, which shows if the questions was answerd right or wrong
     * @param timer The textView which shows the time left
     */
    public void playNewQuestion(final Question question, final Button[] buttons, TextView questionText,
                                final TextView indicator, final TextView timer){
        indicator.setVisibility(View.INVISIBLE);
        //initializes the buttons for this question
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
        //sets the timer
        new CountDownTimer(10000,1000){
            public void onTick(long millisUntilFinished){
                timer.setText("Zeit: " + millisUntilFinished/1000+ "s");
            }
            public void onFinish(){
                if(!question.isValuated){
                    evaluation(buttons, -1 , question, indicator);
                }

            }
        }.start();
    }

    /**
     * Method that valuates if the given answer was right or wrong
     * @param buttons Array of the answer buttons of the GameActivity
     * @param i Arrayindex of the button which was pressed
     * @param question The question which is evaluated
     * @param indicator The TextView which shows if the question is answered right or wrong
     */
    public void evaluation(Button[] buttons, int i, Question question, TextView indicator){
        buttonsDeactivate(buttons);
        //i<0, if no answer was given
        if(i < 0){
            indicator.setText("Zeit vorbei!");
        }else{
            if (buttons[i].getText().equals(question.getAnswers(0))) {
                indicator.setText("RICHTIG!");
            }else {
                indicator.setText("FALSCH!");
            }
        }
        indicator.setVisibility(View.VISIBLE);
        question.isValuated = true;



    }

    /**
     * Method that deacivates the answer buttons
     * @param buttons Array of the answer buttons of the GameActivity
     */
    public void buttonsDeactivate(Button[] buttons){
        for (int i= 0; i<4;i++){
            buttons[i].setClickable(false);
        }
    }

    /**
     * Method that activates the answer buttons
     * @param buttons Array of the answer buttons of the GameActivity
     */
    public void buttonsActivate(Button[] buttons){
        for (int i= 0; i<4;i++){
            buttons[i].setClickable(true);
        }
    }
}

