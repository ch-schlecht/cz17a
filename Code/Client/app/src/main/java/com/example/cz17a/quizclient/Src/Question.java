package com.example.cz17a.quizclient.Src;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stein on 11.01.2018.
 */

public class Question implements Serializable{
    private int id;
    private String questioning;
    private List<String> answers = new ArrayList<String>();
    private Boolean isValuated = false;
    private int dynamicDifficulty;
    private int responseTime;
    private int worth;
    private String topic;
    private double speedInSeconds;
    private boolean isCorrect;
    private int score;

    public List<String> getAnswers() {
        return answers;
    }

    public int getDynamicDifficulty() {
        return dynamicDifficulty;
    }

    public void setDynamicDifficulty(int dynamicDifficulty) {
        this.dynamicDifficulty = dynamicDifficulty;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public int getWorth() {
        return worth;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public double getSpeedInSeconds() {
        return speedInSeconds;
    }

    public void setSpeedInSeconds(double speedInSeconds) {
        this.speedInSeconds = speedInSeconds;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Question (){
    }

    public Boolean getValuated() {
        return isValuated;
    }

    public void setValuated(Boolean valuated) {
        isValuated = valuated;
    }

    public int getAnswertime() {
        if(responseTime >0 ){
            return responseTime;
        }else{
            return 15000;
        }
    }

    public void setAnswertime(int answertime) {
        this.responseTime = answertime;
    }


    /**
     * Sets the answers of this question
     * @param answerlist Stringarray of the answers
     */
    public void setAnswers(List<String> answerlist){
        answers = answerlist;
    }

    /**
     * Sets the question text
     * @param question String of the question text
     */
    public void setQuestioning(String question){
        questioning = question;
    }

    /**
     * Sets the question id
     * @param id int for the question id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Gets the answers of the quetsion
     * @param i the index of the returned answer of the answer array
     * @return String of this answer
     */
    public String getAnswers(int i){
        return answers.get(i);
    }

    /**
     * Gets the question text of this question
     * @return String of this question Text
     */
    public String getQuestioning(){
        return questioning;
    }

    /**
     * Gets the ID of this question
     * @return ID as a int
     */
    public int getId(){
        return this.id;
    }

    public void jsonToQuestion(JSONObject json){
        try {
            this.questioning = json.getString("questioning");
            this.id = json.getInt("id");
            this.worth = json.getInt("worth");
            answers = new ArrayList<String>();
            this.dynamicDifficulty = json.getInt("dynamicDifficulty");
            for(int i = 0; i < 4; i++){
                answers.add(json.getJSONArray("answers").getJSONObject(i).getString("content"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("Questioning: ").append(questioning);
        return sb.toString();
    }
    public void dummyQuestion(){
        questioning = "DUMMY TESTFRAGE ";
        id = 1;
        answers = new ArrayList<String>();
        for (int i = 0; i < 4; i++){
            answers.add("Test Antwort" + i);
        }
    }
}
