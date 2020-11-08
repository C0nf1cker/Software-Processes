package com.example.database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Email del usuario con sesion iniciada, inicialmente a null
    private String currentUserEmail = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //En caso de haber iniciado sesión necesitamos recibir el email del usuario loggeado para
        //comunicarselo al resto de activities
        currentUserEmail = getIntent().getStringExtra("userEmail");
        setContentView(R.layout.activity_main);
    }

    /**
     * Acción que nos lleva a la pantalla de registro de un nuevo usuario
     *
     * @param view
     */
    public void sign(View view) {
        Intent i = new Intent(this, SignActivity.class);
        i.putExtra("userEmail", this.currentUserEmail);
        startActivity(i);
    }

    /**
     * Acción que nos lleva a la pantalla de inicio de sesión
     *
     * @param view
     */
    public void login(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        i.putExtra("userEmail", this.currentUserEmail);
        startActivity(i);
    }

    /**
     * Accion que nos lleva a la pantalla que muestra los mensajes tematica COVID
     *
     * @param view
     */
    public void message(View view) {
        Intent i = new Intent(this, MessageActivity.class);
        i.putExtra("userEmail", this.currentUserEmail);
        startActivity(i);
    }

    /**
     * Accion que nos lleva a la pantalla que muestra el ranking de los usuarios
     *
     * @param view
     */
    public void ranking(View view) {
        Intent i = new Intent(this, RankingActivity.class);
        i.putExtra("userEmail", this.currentUserEmail);
        startActivity(i);
    }

    /**
     * Accion que nos lleva al juego
     *
     * @param view
     */
    public void Play(View view) {
        Intent play = new Intent(this, Game.class);
        //Mandamos el email del usuario loggeado para que actualice su puntuación mientras juega
        play.putExtra("userEmail", this.currentUserEmail);
        startActivity(play);
    }

    /**
     * Accion que nos lleva a la pantalla del perfil del usuario
     *
     * @param view
     */
    public void Profile(View view) {
        Intent profile = new Intent(this, Profile.class);
        //Mandamos el email del usuario loggeado para que muestre sus datos
        profile.putExtra("userEmail", this.currentUserEmail);
        startActivity(profile);
    }

    /**
     * Accion para ir a la pantalla de ajustes
     *
     * @param view
     */
    public void Settings(View view) {
        Intent settings = new Intent(this, Settings.class);
        settings.putExtra("userEmail", this.currentUserEmail);
        startActivity(settings);
    }

    /**
     * Accion para salir de la App
     *
     * @param view
     */
    public void Exit(View view) {
        finish();
    }

}