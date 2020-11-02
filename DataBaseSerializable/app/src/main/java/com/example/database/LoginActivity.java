package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.txtLoginEmail);
        etPassword = (EditText) findViewById(R.id.txtLoginPassword);
    }

    public void goBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public boolean check(String email, String password){
        return (!email.isEmpty() && !password.isEmpty());
    }

    public void login(View view){
        DataBaseSystem db = DataBaseSystem.getInstancia();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (check(email, password)){
            User u = db.login(email, password);
            if (u == null){
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "Bienvenido " + u.getName(), Toast.LENGTH_LONG).show();
            }
        }
        etEmail.setText("");
        etPassword.setText("");
    }
}