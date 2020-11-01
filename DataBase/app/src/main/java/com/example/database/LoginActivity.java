package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private DataBase ddbb;
    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ddbb = new DataBase();
        ddbb.loadUsers();
        ddbb.loadMessages();
        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.txtLoginEmail);
        etPassword = (EditText) findViewById(R.id.txtLoginPassword);
    }

    public void goBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void logIn(View view){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if(comprobarCampos(email,password)){
            User u = ddbb.loadUser(email);
            Toast.makeText(this, "Usuario encontrado.", Toast.LENGTH_LONG).show();
            if(u.getPassword().equals(password))
                Toast.makeText(this, "Usuario loggeado.", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(this, "Campos solicictados vacios.", Toast.LENGTH_LONG).show();
    }

    private boolean comprobarCampos(String email, String password) {
        if(email.isEmpty()||password.isEmpty())
            return false;
        return true;
    }
}