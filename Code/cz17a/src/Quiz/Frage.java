/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quiz;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Christian Schlecht
 */
public class Frage {
    
    private String frage;
    private TreeMap<Integer,String> antworten;
    private int richtigeAntwort;
    
    /**
     * Konstruktor der Frage
     * @param frage Frage
     * @param richtigeAntwort index der richtigen Antwort, spaeter ID?
     * @param antworten beliebige Anzahl an Antworten
     */
    public Frage(String frage, int richtigeAntwort, String... antworten){
        this.frage = frage;
        this.richtigeAntwort = richtigeAntwort;
    
        this.antworten = new TreeMap<Integer,String>();
        for(int i = 0; i < antworten.length; i++){
            this.antworten.put(i, antworten[i]);
        }
    }

    /**
     * Getter fuer alle Antworten
     * @return TreeMap mit ID, Antwort
     */
    public TreeMap<Integer,String> getAntworten(){
        return this.antworten;
    }
    
    /**
     *Getter fuer korrekte Antwort
     * @return korrekte Antwort
     */
    public String getKorrekteAntwort(){
        return this.antworten.get(this.richtigeAntwort);
    }
    
    /**
     * ueberpruefe ID auf richtige Antwort
     * @param answer ID der gegebenen Antwort
     * @return true wenn richtig, false anderweitig
     */
    public boolean checkAnswer(int answer){
        if(answer == this.richtigeAntwort){
            System.out.println("Richtig!");
            return true;
        }
        else{
            System.out.println("Falsch! Richtig wäre: " + this.antworten.get(this.richtigeAntwort));
            return false;
        }
    }
    
    /**
     * Antwort-Methode mit UserInput
     * @return true falls Antwort richtig, false anderweitig, inkl. println
     * @see checkAnswer
     */
    public boolean answer(){
        System.out.println("Zahl der Antwort eingeben, welche Ihrer Meinung nach korrekt ist:");
        Scanner s = new Scanner(System.in);
        int userAntwort = -1;
        try{
            userAntwort = s.nextInt();
            if(userAntwort < 0 || userAntwort > this.antworten.size() - 1){
                System.out.println("Answer out of Range, aborting");
                return false;
            }
        }
        catch(InputMismatchException imEx){
            imEx.printStackTrace(System.err);
            return false;
        }
        return checkAnswer(userAntwort);
    }
    /**
     * toString zum Ausgeben der Fragen und allen Antworten
     * @return String mit Frage und zugehoerigen Antwortmoeglichkeiten
     */
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(frage).append("\n").append("----------------------------------").append("\n");
        for(int i = 0; i < this.antworten.size(); i++){
            s.append(i).append(":").append(this.antworten.get(i)).append("\n");
        }
        return s.toString();
    }
    
}
