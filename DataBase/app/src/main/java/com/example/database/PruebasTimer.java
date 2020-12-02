package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class PruebasTimer extends AppCompatActivity {
    //Objeto que representa el temporizador
    private Temporizador timer;
    private TextView timeLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas_timer);
        timeLeft = (TextView) findViewById(R.id.timeText);
        timer = new Temporizador(timeLeft);
        timer.star();
    }

    public void acertar(View view){
        timer.acertar();
    }

    public void fallar(View view){
        timer.fallar();
    }


}