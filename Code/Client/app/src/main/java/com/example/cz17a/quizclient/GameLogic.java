package com.example.cz17a.quizclient;

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
}
