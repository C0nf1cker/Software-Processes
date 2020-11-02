package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Juego extends AppCompatActivity {
    //showValue es el textview correspondiente a la puntuación que me va a ir variando en función de los clicks dados al círculo
    TextView showValue;
    // el counter es el contador de los clicks dados al círculo necesarios para contar la puntuación
    int counter =0;
    //necesitamos unbooleano pressed para saber si se ha presionado en una partida el círculo ya que,
    //en caso de que si, no cuente como puntuación múltiples clicks sobre el mismo círculo del mismo nivel, sería ilógico
    boolean pressed=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        //innicializamos pressed a false
        pressed=false;
        //coloca el círculo con id: button12 en posiciones aleatorias
        Button button = (Button)findViewById(R.id.button12);
        AbsoluteLayout.LayoutParams absParams =
                (AbsoluteLayout.LayoutParams)button.getLayoutParams();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels-200;
        int height = displaymetrics.heightPixels-500;


        Random r = new Random();

        absParams.x =  r.nextInt(width );
        absParams.y =  r.nextInt(height );
        button.setLayoutParams(absParams);

        //necesario para mostrar la puntuación síncrona a tiempo real en la casilla textView counterValue
        showValue = (TextView) findViewById(R.id.counterValue);


    }
    //botón volver a inicio
    public void goMain(View view){
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
        counter=0;
        showValue.setText(Integer.toString(counter));
    }
    //al pulsar el botón del círculo se mostrará una notificación de victoria seguido por un incremento del contador para las puntuaciones
    public void victoryNotification_increaseScore(View view){
       if (!pressed) {
            Toast.makeText(this, "lo has encontrado!", Toast.LENGTH_SHORT).show();
            //en cada click de este botón el counter se incrementará
            counter++;
            showValue.setText(Integer.toString(counter));
        }
       pressed=true;


    }
    //correspondiente al botón jugar de nuevo, necesario para que las puntuaciones sean en función del número de partidas
    public void playAgain(View view){
        //innicializamos pressed a false
        pressed=false;
        //coloca el círculo con id: button12 en posiciones aleatorias
        Button button = (Button)findViewById(R.id.button12);
        AbsoluteLayout.LayoutParams absParams =
                (AbsoluteLayout.LayoutParams)button.getLayoutParams();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels-200;
        int height = displaymetrics.heightPixels-500;


        Random r = new Random();

        absParams.x =  r.nextInt(width );
        absParams.y =  r.nextInt(height );
        button.setLayoutParams(absParams);
        //necesario para mostrar la puntuación síncrona a tiempo real en la casilla textView counterValue
        showValue = (TextView) findViewById(R.id.counterValue);

    }



}