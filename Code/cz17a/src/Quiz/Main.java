/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Quiz;

/**
 *
 * @author Christian-PC2
 */
public class Main {
    
    public static void main(String[] args){
        
        Frage frage = new Frage("Wie heiße ich?", 0, "Christian", "Dieter", "Peter", "Detlef");
        System.out.println(frage.toString());
        System.out.println(frage.getAntworten());
        System.out.println(frage.getKorrekteAntwort());
        frage.answer();
        
        
    }
    
}
