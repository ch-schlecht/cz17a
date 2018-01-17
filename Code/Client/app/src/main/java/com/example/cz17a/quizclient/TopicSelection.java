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

public class TopicSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_selection);
        final Button topic1 = findViewById(R.id.butTopic1);
        final TopicHandler topicHandler = new TopicHandler(topic1);
        topicHandler.setUpActivity();
        addButton();
        topic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGame();
            }
        });
    }
    public void goToGame(){
        Intent intent = new Intent(this,  GameActivity.class);
        startActivity(intent);
    }

    public void addButton(){
        System.out.println("Neuer Button");
        Button b = new Button(this);
        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        linLayout.addView(b);
    }


}
