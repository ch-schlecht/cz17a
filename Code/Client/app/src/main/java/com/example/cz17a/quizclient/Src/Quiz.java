package com.example.cz17a.quizclient.Src;

import android.widget.Button;

/**
 * Created by Willy Steinbach on 30.01.2018.
 */

/**
 * Representation for a single quiztype/topic
 */
public class Quiz {
   private int id;
   private String title;
   private int length;
   private Button topicButton;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getLength() {
        return length;
    }

    public Button getTopicButton() {
        return topicButton;
    }

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("ID: ").append(id).append(", Title: ").append(title).append(", Length: ")
                .append(length).append("\n");
        return retString.toString();
    }
}
