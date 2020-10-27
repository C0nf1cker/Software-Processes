package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Play(View view){
        Intent play = new Intent(this, Juego.class);
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
    public void Ranking(View view){
        Intent ranking = new Intent(this, Ranking.class);
        startActivity(ranking);
    }
    public void Exit(View view){
        finish();
        System.exit(0);
    }

}