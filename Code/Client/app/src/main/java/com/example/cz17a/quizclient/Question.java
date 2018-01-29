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

    /**
     * Sets the answers of this question
     * @param answerlist Stringarray of the answers
     */
    public void setAnswers(String[] answerlist){
        answers = answerlist;
    }

    /**
     * Sets the question text
     * @param question String of the question text
     */
    public void setQuestionText(String question){
        questionText = question;
    }

    /**
     * Sets the question id
     * @param id int for the question id
     */
    public void setQuestionID(int id){
        questionID = id;
    }

    /**
     * Gets the answers of the quetsion
     * @param i the index of the returned answer of the answer array
     * @return String of this answer
     */
    public String getAnswers(int i){
        return answers[i];
    }

    /**
     * Gets the question text of this question
     * @return String of this question Text
     */
    public String getQuestionText(){
        return questionText;
    }

    /**
     * Gets the ID of this question
     * @return ID as a int
     */
    public int getID(){
        return questionID;
    }
}
