package com.example.cz17a.quizclient.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.cz17a.quizclient.Login.User;
import com.example.cz17a.quizclient.Src.Quizzes;
import com.example.cz17a.quizclient.R;

/**
 * Created by Willy Steinbach
 */

public class TopicSelection extends AppCompatActivity {
    public static Quizzes quizzes;
    public static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_selection);
        addButton();
    }

    /**
     * Method that starts the GameActivity and gives it the quizId
     */
    public void goToGame(int quizId){
        Intent intent = new Intent(this,  LobbyActivity.class);
        LobbyActivity.quizzes = quizzes;
        LobbyActivity.user = user;
        intent.putExtra("quizId", quizId);
        startActivity(intent);
    }

    /**
     * Method that creates the topic buttons
     */
    public void addButton(){
        for(int i = 0; i<quizzes.getTopics().size(); i++){
            final int finalI = i;
            Button b = new Button(this);
            b.setText(quizzes.getTopics().get(i).getTitle());
            LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
            linLayout.addView(b);
            quizzes.getTopics().get(i).setTopicButton(b);
            //Creates the ClickListener for each button
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToGame(quizzes.getTopics().get(finalI).getId());
                }
            });
        }
    }

}
