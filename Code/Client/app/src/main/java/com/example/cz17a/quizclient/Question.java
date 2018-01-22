package com.example.cz17a.quizclient;

/**
 * Created by stein on 11.01.2018.
 */

public class Question {
    String questionText;
    int questionID;
    String[] answers;
    Boolean isValuated = false;

    public Question (){

    }
    public void setAnswers(String[] answerlist){
        answers = answerlist;
    }
    public void setQuestionText(String question){
        questionText = question;
    }
    public void setQuestionID(int id){
        questionID = id;
    }
    public String getAnswers(int i){
        return answers[i];
    }
    public String getQuestionText(){
        return questionText;
    }
    public int getID(){
        return questionID;
    }
}
