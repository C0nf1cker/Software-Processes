package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Math.random;

public class Message extends AppCompatActivity {

    private ProgressDialog progress;
    private static String TAG = "MainActivity ";
    Button SaltarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Random r = new Random();
        int n = r.nextInt(5);
        //Al darle al boton inicializamos barra de carga y vamos a la otra pantalla
        SaltarPantalla = (Button) findViewById(R.id.button11);

        Toast.makeText(this,"ponte la mascarilla", Toast.LENGTH_LONG).show();
    }
    /*public void goMain(View view){
        Intent main = new Intent(this, Juego.class);
        startActivity(main);
    }*/

    //Carga la barra y al finalizar te lleva al juego
    public void cargar(View view){
        progress=new ProgressDialog(this);
        progress.setMessage("Cargando pantalla....");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setProgress(0);
        progress.show();

        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while(jumpTime < totalProgressTime) {
                    try {
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                        sleep(200);
                    }
                    catch (InterruptedException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        };
        t.start();
        Intent main = new Intent(this, Game.class);
        startActivity(main);

    }

}