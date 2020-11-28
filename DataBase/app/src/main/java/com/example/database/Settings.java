package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {

    //Email del usuario loggeado actualmente
    private String currentUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentUserEmail = getIntent().getStringExtra("userEmail");
        setContentView(R.layout.activity_settings);
    }

    /**
     * Accion de volver al menu principal
     *
     * @param view
     */
    public void goMain(View view) {
        Intent main = new Intent(this, MainMenu.class);
        main.putExtra("userEmail", this.currentUserEmail);
        startActivity(main);
    }
}