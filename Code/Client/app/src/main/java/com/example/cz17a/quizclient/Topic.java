package com.example.cz17a.quizclient;

import android.widget.Button;

/**
 * Created by Willy Steinbach on 30.01.2018.
 */

/**
 * Representation for a single quiztype/topic
 */
public class Topic {
    int id;
    String title;
    int maxParticipants;
    int minParticipants;
    int length;
    Button topicButton;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setMinParticipants(int minParticipants) {
        this.minParticipants = minParticipants;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setTopicButton(Button topicButton) {
        this.topicButton = topicButton;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public int getMinParticipants() {
        return minParticipants;
    }

    public int getLength() {
        return length;
    }

    public Button getTopicButton() {
        return topicButton;
    }

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("ID: ").append(id).append(", Title: ").append(title).append(", Length: ")
                .append(length).append(", MinParticipants: ").append(minParticipants)
                .append(", MaxParticipants: ").append(maxParticipants).append("\n");
        return retString.toString();
    }
}
