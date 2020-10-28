package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Math.random;

public class Message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Random r = new Random();
        int n = r.nextInt(5);

        Toast.makeText(this,"ponte la mascarilla", Toast.LENGTH_LONG).show();
    }
    public void goMain(View view){
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

}