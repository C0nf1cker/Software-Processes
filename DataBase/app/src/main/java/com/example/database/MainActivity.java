package com.example.database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DataBase ddbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sign(View view){
        Intent i = new Intent(this, SignActivity.class);
        i.putExtra("DB",ddbb);
        startActivity(i);
    }

    public void login(View view){
        Intent i = new Intent(this, LoginActivity.class);
        i.putExtra("DB",ddbb);
        startActivity(i);
    }

    public void message(View view){
        Intent i = new Intent(this, MessageActivity.class);
        i.putExtra("DB",ddbb);
        startActivity(i);
    }

}