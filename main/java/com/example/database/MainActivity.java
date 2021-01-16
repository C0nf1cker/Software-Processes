package com.example.database;

import android.app.Activity;
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

    public void sign(View view) {
        Intent i = new Intent(this, SignActivity.class);
        startActivity(i);
    }

    public void login(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void message(View view) {
        Intent i = new Intent(this, MessageActivity.class);
        startActivity(i);
    }

    public void ranking(View view){
        Intent i = new Intent(this, RankingActivity.class);
        startActivity(i);
    }
    public void Play(View view){
        Intent play = new Intent(this, Game.class);
        startActivity(play);
    }
    public void Profile(View view){
        Intent profile = new Intent(this, Profile.class);
        startActivity(profile);
    }
    public void Settings(View view){
        Intent settings = new Intent(this, Settings.class);
        startActivity(settings);
    }
    public void Exit(View view){
        finish();
    }


}