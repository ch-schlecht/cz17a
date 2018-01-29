package com.example.cz17a.quizclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class TopicSelection extends AppCompatActivity {
    final TopicHandler topicHandler = new TopicHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_selection);
        ArrayList<Button> topicButtons = new ArrayList<>();
        addButton(topicHandler.getMaxID(), topicButtons);
    }

    /**
     * Method that starts the GameActivity
     */
    public void goToGame(){
        Intent intent = new Intent(this,  GameActivity.class);
        startActivity(intent);
    }

    /**
     * Method that creates the topic buttons
     * @param count the ammount of buttons to be added
     * @param topicButtons the ArrayList of the topic buttons
     */
    public void addButton(int count, ArrayList<Button> topicButtons){
        for(int i = 0; i<count; i++){
            Button b = new Button(this);
            LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
            linLayout.addView(b);
            topicButtons.add(b);
            //Creates the ClickListener for each button
            topicButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToGame();
                }
            });
        }
        topicHandler.setUpActivity(topicButtons);

    }


}
