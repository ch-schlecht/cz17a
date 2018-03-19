package com.example.cz17a.quizclient.Src;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.cz17a.quizclient.GameLogic;

import org.w3c.dom.Text;

/**
 * Created by Willy on 19.03.2018.
 */

public class Timer {
    private int time;
    private int currentState;
    private CountDownTimer countDownTimer;
    private GameLogic gameLogic;
    private Question question;
    public Timer(int time, final GameLogic gameLogic, Question question){
        this.time = time;
        this.gameLogic = gameLogic;
        this.question = question;
    }
    public void startTimer(){
        countDownTimer = new CountDownTimer(time,1000){
            public void onTick(long millisUntilFinished){
                currentState = (int) millisUntilFinished/1000;
                gameLogic.getTimer().setText("Zeit: " + millisUntilFinished/1000+ "s");
            }
            public void onFinish(){
                    gameLogic.evaluation(-1 , question);
                }
        }.start();
    }
    public void stopTimer(){
        countDownTimer.cancel();
    }
    public int getResponseTime(){
        return  time - currentState;
    }
}
