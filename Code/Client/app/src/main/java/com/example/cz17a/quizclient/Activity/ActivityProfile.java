package com.example.cz17a.quizclient.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cz17a.quizclient.Login.LoginActivity;
import com.example.cz17a.quizclient.Login.User;
import com.example.cz17a.quizclient.R;

public class ActivityProfile extends AppCompatActivity {
  //  public static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView username = findViewById(R.id.nicknameText);
        TextView email = findViewById(R.id.emailText);

        //Load UserID
        SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
        String emailS = sp.getString("mail", "no mail");
        String nickname = sp.getString("nickname", "no nickname");


        username.setText(nickname);
        email.setText(emailS);

        Button logout = findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

        private void logout(){

            //Save in Shared Preference
            SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
            SharedPreferences.Editor Ed = sp.edit();
            Ed.putBoolean("loggedIn",false);
            Ed.putString("uId","-1");
            Ed.putString("name","");
            Ed.putString("mail","");
            Ed.commit();


            //TODO go to login-->
            Intent intent = new Intent(this, LoginActivity.class);
            // MainActivity.user = user;
            startActivity(intent);

    }
}
