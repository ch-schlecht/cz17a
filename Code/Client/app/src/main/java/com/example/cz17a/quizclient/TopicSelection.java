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
    public void goToGame(){
        Intent intent = new Intent(this,  GameActivity.class);
        startActivity(intent);
    }

    public void addButton(int anzahl, ArrayList<Button> topicButtons){
        System.out.println("Neuer Button");
        for(int i = 0; i<anzahl; i++){
            Button b = new Button(this);
            LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
            linLayout.addView(b);
            topicButtons.add(b);
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
