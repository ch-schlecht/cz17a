package com.example.cz17a.quizclient;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cz17a.quizclient.ServerClient.ServerCommunication;
import com.example.cz17a.quizclient.ServerClient.SocketCommunication;
import com.example.cz17a.quizclient.Src.Question;
import com.example.cz17a.quizclient.Src.Jackpot;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Willy Steinbach on 11.01.2018.
 */

public class GameLogic {
    private int quizId;
    private int questioncount;
    private Question questionlist[];
    private ServerCommunication servCom;
    private Button[] buttons;
    private TextView questionText;
    private TextView indicator;
    private TextView timer;
    private SocketCommunication socketCommunication;
    private int gameId;
    Jackpot jackpot;

    public GameLogic(int quizID, final Button[] buttons, TextView questionText,
                     final TextView indicator, final TextView timer){
        this.quizId = quizID;
        this.buttons = buttons;
        this.questionText = questionText;
        this.indicator = indicator;
        this.timer = timer;
        this.jackpot = new Jackpot();
        servCom = new ServerCommunication();
        //questionlist = servCom.getQuestions(quizId);
        //questioncount = questionlist.length;
    }

    /**
     * Method for playing a specified Question
     * Modifies Buttons and TextFields of the GameActivity
     * @param question The question that is going to be played
     */
    public void playNewQuestion(final Question question){
        indicator.setVisibility(View.INVISIBLE);
        //initializes the buttons for this question
        for(int i = 0; i<4; i++){
            final int finalI = i;
            buttons[i].setText(question.getAnswers(i));
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    evaluation(buttons, finalI , question, indicator);
                    socketCommunication.sendAnswer(buttons[finalI].getText().toString());
                }
            });
        }
        questionText.setText(question.getQuestioning());
        buttonsActivate(buttons);
        //sets the timer
        new CountDownTimer(question.getAnswertime(),1000){
            public void onTick(long millisUntilFinished){
                timer.setText("Zeit: " + millisUntilFinished/1000+ "s");
            }
            public void onFinish(){
                if(!question.getValuated()){
                    evaluation(buttons, -1 , question, indicator);
                    socketCommunication.sendAnswer(null);
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
        socketCommunication.sendAnswer(buttons[i].getText().toString());
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
        question.setValuated(true);
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

    private void sendQuestionEvaluation(Question question) {
        ServerCommunication serverCommunication = new ServerCommunication();
        JSONObject json = new JSONObject();
        try {
            json.put("isJackpot", jackpot.isActive());
            json.put("isCorrect", question.isCorrect());
            json.put("speedInSeconds", question.getSpeedInSeconds());
            json.put("score", question.getScore());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        serverCommunication.postPlayedQuestion(gameId, question.getId(), json);
    }
}

