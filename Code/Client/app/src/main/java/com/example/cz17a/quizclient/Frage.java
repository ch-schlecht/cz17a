package com.example.cz17a.quizclient;

/**
 * Created by stein on 11.01.2018.
 */

public class Frage {
    String frageText;
    int questionID;
    String[] antworten;
    Boolean isValuated = false;

    public Frage (){
        //frage = "Test Frage?";

    }
    public void setAnswers(String[] questions){
        antworten = questions;
    }
    public void setFrageText(String question){
        frageText = question;
    }
    public void setQuestionID(int id){
        questionID = id;
    }
    public String getAntwort(int i){
        return antworten[i];
    }
    public String getFragenText(){
        return frageText;
    }
    public int getID(){
        return questionID;
    }
}
