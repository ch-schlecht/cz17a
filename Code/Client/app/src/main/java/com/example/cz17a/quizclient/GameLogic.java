package com.example.cz17a.quizclient;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.cz17a.quizclient.ServerClient.ServerCommunication;
import com.example.cz17a.quizclient.Src.Question;
import com.example.cz17a.quizclient.Src.Jackpot;
import com.example.cz17a.quizclient.Src.Timer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;

/**
 * Created by Willy Steinbach on 11.01.2018.
 */

public class GameLogic {
    private int quizId;
    private Question[] questions;
    private Button[] buttons;
    private TextView questionText;
    private TextView indicator;
    private TextView scoreView;
    private TextView timerView;
    private TextView jackpotView;
    private int gameId;
    Jackpot jackpot;
    private Timer countdownTimer;
    private int score;
    private int currentQuestionIndex = 0;

    public GameLogic(int quizId, int gameId, Question[] questions, final Button[] buttons, final TextView questionText,
                     final TextView indicator, final TextView timer, final TextView scoreView, final TextView jackpotView) {
        this.quizId = quizId;
        this.gameId = gameId;
        this.questions = questions;
        this.buttons = buttons;
        this.questionText = questionText;
        this.indicator = indicator;
        this.timerView = timer;
        this.jackpot = new Jackpot();
        this.score = 0;
        this.scoreView = scoreView;
        this.jackpotView = jackpotView;
    }

    /**
     * Method for playing a specified Question
     * Modifies Buttons and TextFields of the GameActivity
     */
    public void playNewQuestion() {
        final Question question = questions[currentQuestionIndex];
        //sets the timer
        jackpotView.setText(jackpot.getAmount());
        countdownTimer = new Timer(this, question);
        indicator.setVisibility(View.INVISIBLE);
        //initializes the buttons for this question
        List<String> shuffledAnswers = new ArrayList<>();
        for(String answer : question.getAnswers()) {
            shuffledAnswers.add(answer);
        }
        Collections.shuffle(shuffledAnswers);
        for(int i = 0; i < buttons.length; i++){
            final int finalI = i;
            buttons[i].setText(shuffledAnswers.get(i));
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    countdownTimer.stopTimer();
                    evaluation(finalI , question);
                }
            });
        }
        questionText.setText(question.getQuestioning());
        buttonsActivate(buttons);
        countdownTimer.startTimer();
    }

    /**
     * Method that valuates if the given answer was right or wrong
     * @param pressedButtonIndex Arrayindex of the button which was pressed
     * @param question The question which is evaluated
     */
    public void evaluation(int pressedButtonIndex, Question question) {
        buttonsDeactivate(buttons);
        //i<0, if no answer was given
        int score = 0;
        double responseTime = countdownTimer.getResponseTime();
        if(pressedButtonIndex < 0){
            question.setCorrect(false);
            indicator.setText("Zeit vorbei!");
        }else{
            if (buttons[pressedButtonIndex].getText().equals(question.getAnswers(0))) {
                question.setCorrect(true);
                if(jackpot.isActive()) {
                    score = jackpot.payout(responseTime);
                }
                else {
                    score = question.getWorth();
                }
                indicator.setText("RICHTIG!");
            }else {
                question.setCorrect(false);
                indicator.setText("FALSCH!");
                buttons[pressedButtonIndex].setBackgroundColor(Color.RED);
            }
        }
        for(Button button : buttons) {
            if(button.getText().equals(question.getAnswers(0)))
                button.setBackgroundColor(Color.GREEN);
        }
        this.score += score;
        scoreView.setText(score);
        question.setSpeedInSeconds(responseTime);
        question.setScore(score);
        sendQuestionEvaluation(question);
        indicator.setVisibility(View.VISIBLE);
        question.setValuated(true);
        currentQuestionIndex++;
    }

    /**
     * Method that deacivates the answer buttons
     * @param buttons Array of the answer buttons of the GameActivity
     */
    public void buttonsDeactivate(Button[] buttons) {
        for (int i= 0; i < buttons.length;i++){
            buttons[i].setClickable(false);
        }
    }

    /**
     * Method that activates the answer buttons
     * @param buttons Array of the answer buttons of the GameActivity
     */
    public void buttonsActivate(Button[] buttons) {
        for (int i= 0; i < buttons.length; i++){
            buttons[i].setClickable(true);
        }
    }

    private void sendQuestionEvaluation(Question question) {
        JSONObject json = new JSONObject();
        try {
            json.put("isJackpot", jackpot.isActive());
            json.put("isCorrect", question.isCorrect());
            json.put("speedInSeconds", question.getSpeedInSeconds());
            json.put("score", question.getScore());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ServerCommunication.postPlayedQuestion(gameId, question.getId(), json);
    }

    public TextView getTimerView() {
        return this.timerView;
    }
}

