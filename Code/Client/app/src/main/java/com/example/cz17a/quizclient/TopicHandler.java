package com.example.cz17a.quizclient;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by stein on 17.01.2018.
 */

public class TopicHandler {
    String[] titles;
    int[] IDs;
    public TopicHandler(){
        ServerCommunication servCom = new ServerCommunication();
        IDs = servCom.getTopicIDs();
        titles = servCom.getTopicTitles();

    }
    public void setUpActivity(ArrayList<Button> topicButtons){
        for(int i = 0;i<topicButtons.size();i++){
            topicButtons.get(i).setText(titles[i]);
        }
    }
    public int getMaxID(){
        return IDs.length;
    }

}
