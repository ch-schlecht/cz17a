package com.example.cz17a.quizclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class TopicSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_selection);
        final Button topic1 = findViewById(R.id.butTopic1);
        final TopicHandler topicHandler = new TopicHandler(topic1);
        topicHandler.setUpActivity();
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


}
