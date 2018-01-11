package com.example.cz17a.quizclient;

/**
 * Created by stein on 11.01.2018.
 */

public class Frage {
    String frage;
    String[] antworten = new String[4];
    Boolean isValuated = false;

    public Frage (){
        //frage = "Test Frage?";
        for(int i = 0; i<4; i++){
            antworten[i] = "Antwort " + i;
        }
    }
    public String getAntwort(int i){
        return antworten[i];
    }
    public String getFragenText(){
        return frage;
    }
}
