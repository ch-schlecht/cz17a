package com.example.cz17a.quizclient;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by stein on 17.01.2018.
 */

public class TopicHandler {
    String[] titles;
    int[] IDs;
    Button topicButtonView;
    public TopicHandler(View view){
        ServerCommunication servCom = new ServerCommunication();
        IDs = servCom.getTopicIDs();
        titles = servCom.getTopicTitles();
        topicButtonView = (Button)view;
    }
    public void setUpActivity(){
        Button[] topicButtons = new Button[IDs.length];
        topicButtonView.setText(titles[0]);

    }

}
