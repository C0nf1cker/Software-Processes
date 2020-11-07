package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private UsersDataBase ddbb;
    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ddbb = new UsersDataBase(this);
        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.txtLoginEmail);
        etPassword = (EditText) findViewById(R.id.txtLoginPassword);
    }

    public void goBack(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


    /**
     * Metodo que coge los datos introducidos por el usuario en los campos correspondientes
     * y lo busca en la base de datos llamando al logIn de esta
     *
     * @param view
     */
    public void logIn(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (checkFields(email, password)) {
            User u = ddbb.logIn(email, password);
            if (u != null)
                Toast.makeText(this, "Usuario loggeado.", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Contraseña  incorrecta", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Campos solicictados vacios.", Toast.LENGTH_LONG).show();
    }

    /**
     * Metodo auxiliar usado para comprobar que los campos email y password no estan vacios
     *
     * @param email
     * @param password
     * @return true en caso de que no esten vacios y false si lo estan
     */
    private boolean checkFields(String email, String password) {
        if (email.isEmpty() || password.isEmpty())
            return false;
        return true;
    }
}