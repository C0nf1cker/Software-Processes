package com.example.database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sign(View view){
        Intent i = new Intent(this, SignActivity.class);
        startActivity(i);
    }

    public void login(View view){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void message(View view){
        Intent i = new Intent(this, MessageActivity.class);
        startActivity(i);
    }

    public void ranking(View view){
        Intent i = new Intent(this, RankingActivity.class);
        startActivity(i);
    }

}