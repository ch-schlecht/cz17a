package com.example.cz17a.quizclient.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.cz17a.quizclient.Login.User;
import com.example.cz17a.quizclient.Src.Quizzes;
import com.example.cz17a.quizclient.R;
import com.example.cz17a.quizclient.ServerClient.ServerCommunication;

/**
 * Created by Willy Steinbach
 */

public class MainActivity extends AppCompatActivity {
    public static User user;
    ServerCommunication servCom;
    Quizzes quizzes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        servCom = new ServerCommunication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button playgame = findViewById(R.id.playbutton);
        Button profile = findViewById(R.id.profileButton);
        playgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTopicSelection(view);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProfile();
            }
        });
    }
    /**
     * Starts the activity which shows the topic selection
     * @param view
     */
    public void goToTopicSelection(View view){
        Intent intent = new Intent(this,  TopicSelection.class);
        quizzes = new Quizzes(servCom);
        quizzes.setUpQuizzes();
        TopicSelection.quizzes = quizzes;
        startActivity(intent);
    }
    public void goToProfile(){
        Intent intent = new Intent(this, ActivityProfile.class);
        ActivityProfile.user = user;
        startActivity(intent);
    }

}
