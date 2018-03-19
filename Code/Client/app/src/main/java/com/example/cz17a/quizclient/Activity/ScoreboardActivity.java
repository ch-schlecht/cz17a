package com.example.cz17a.quizclient.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cz17a.quizclient.R;

public class ScoreboardActivity extends AppCompatActivity {

    private TextView player1;
    private TextView player2;
    private TextView player3;
    private TextView player4;
    private TextView player5;
    private TextView score1;
    private TextView score2;
    private TextView score3;
    private TextView score4;
    private TextView score5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        player3 = findViewById(R.id.player3);
        player4 = findViewById(R.id.player4);
        player5 = findViewById(R.id.player5);
        score1 = findViewById(R.id.score1);
        score2 = findViewById(R.id.score2);
        score3 = findViewById(R.id.score3);
        score4 = findViewById(R.id.score4);
        score5 = findViewById(R.id.score5);
    }

    public void createScoreboard(String playerWithScores){
        String[][] separated = separatePlayerscores(playerWithScores);
        String[][] sorted = sortedByScoreDescending(separated);

        player1.setText(sorted[0][0]);
        score1.setText(sorted[0][1]);
        player2.setText(sorted[1][0]);
        score2.setText(sorted[1][1]);
        player3.setText(sorted[2][0]);
        score3.setText(sorted[2][1]);
        player4.setText(sorted[3][0]);
        score4.setText(sorted[3][1]);
        player5.setText(sorted[4][0]);
        score5.setText(sorted[4][1]);

    }

    private String[][] sortedByScoreDescending(String[][] separated){
        String[][] sorted = null;
        boolean changed = false;
        //for (){

       // }
      return null;
    }

    /**
     * Method that separates the String into the playerindex and the associated score.
     * @param playerWithScores
     * @return 's a String[][]
     */
    private String[][] separatePlayerscores(String playerWithScores){
        String[][] sAA = null;
        String[] sA = playerWithScores.split(",");
        for (int i=0; i<sA.length; i++){
            String[] s = sA[i].split(": ");
            sAA[i][0] =  s[0].substring(1,s[0].length()-2);
            sAA[i][1] = s[1].substring(1,s[1].length()-2);
        }
        return sAA;
    }

}
