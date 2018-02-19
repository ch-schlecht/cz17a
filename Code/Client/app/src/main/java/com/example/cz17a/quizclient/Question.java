package com.example.cz17a.quizclient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by stein on 11.01.2018.
 */

public class Question {
    String questionText;
    String questionID;
    String[] answers;
    Boolean isValuated = false;
    int answertime;

    public int getAnswertime() {
        if(answertime >0 ){
            return answertime;
        }else{
            return 15000;
        }

    }

    public void setAnswertime(int answertime) {
        this.answertime = answertime;
    }

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
    public void setQuestionID(String id){
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
    public String getID(){
        return questionID;
    }
    public void jsonToQuestion(JSONObject json){
        try {
            this.questionText = json.getString("questioning");
            this.questionID = json.getString("questioning");
            answers = new String[4];
            for(int i = 0; i<4;i++){

                answers[i] = json.getJSONArray("answers").getJSONObject(i).getString("content");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void dummyQuestion(){
        questionText = "DUMMY TESTFRAGE ";
        questionID = "1";
        answers = new String[4];
        for (int i =0; i<4;i++){
            answers[i]= "Test Antwort";
        }
    }
}
