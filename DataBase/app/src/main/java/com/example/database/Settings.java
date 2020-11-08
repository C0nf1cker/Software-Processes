package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {
    private String currentUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentUserEmail = getIntent().getStringExtra("userEmail");
        setContentView(R.layout.activity_settings);
    }
    public void goMain(View view){
        Intent main = new Intent(this, MainActivity.class);
        main.putExtra("userEmail",this.currentUserEmail);
        startActivity(main);
    }
}