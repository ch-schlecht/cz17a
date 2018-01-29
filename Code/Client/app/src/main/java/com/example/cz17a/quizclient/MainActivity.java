package com.example.cz17a.quizclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button playgame = findViewById(R.id.playbutton);
        playgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTopicSelection(view);
            }
        });
    }
    /**
     * Starts the activity which shows the topic selection
     * @param view
     */
    public void goToTopicSelection(View view){
        Intent intent = new Intent(this,  TopicSelection.class);
        startActivity(intent);
    }

}
