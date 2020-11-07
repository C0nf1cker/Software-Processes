package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Profile extends AppCompatActivity {

    private String Nombre;
    private String Email;
    private String Password;
    private String Nacionalidad;

    public Profile(String nombre, String email, String passwd, String nacionalidad) {
        Nombre = nombre;
        Email = email;
        Password = passwd;
        Nacionalidad = nacionalidad;
    }
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
    public void goMain(View view){
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
}