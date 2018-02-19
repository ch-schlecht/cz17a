package com.example.cz17a.quizclient.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cz17a.quizclient.Login.User;
import com.example.cz17a.quizclient.R;

public class ActivityProfile extends AppCompatActivity {
    public static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView username = findViewById(R.id.nicknameText);
        TextView email = findViewById(R.id.emailText);
        username.setText(user.getNickname());
        email.setText(user.getMail());
    }
}
