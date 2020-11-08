package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignActivity extends AppCompatActivity {
    private String currentUserEmail;
    private UsersDataBase ddbb;
    private EditText etName, etSurname, etEmail, etPassword, etConfirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        currentUserEmail = getIntent().getStringExtra("userEmail");

        //Inicializamos nuestra BBDD de usuarios
        ddbb = new UsersDataBase(this);

        //Inicializacion de elementos de la interfaz
        etEmail = (EditText) findViewById(R.id.txtEmail);
        etPassword = (EditText) findViewById(R.id.txtPassword);
        etConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        etName = (EditText) findViewById(R.id.txtName);
        etSurname = (EditText) findViewById(R.id.txtSurname);
    }

    public void goBack(View view) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("userEmail",this.currentUserEmail);
        startActivity(i);
    }

    /**
     * Metodo para registrar un nuevo usuario, comprobando que los campos han sido rellenados
     * correctamente y llamando al metodo registrarse de la base de datos de usuarios.
     *
     * @param view
     */
    public void sigIn(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        if (comprobarCampos(email, password, confirmPassword, name, surname)) {
            if (confirmPassword.equals(password)) {
                boolean correctSignIn = ddbb.sigIn(email, name, surname, password);
                if (correctSignIn) {
                    Toast.makeText(this, "Usuario registrado correctamente.", Toast.LENGTH_LONG).show();
                    currentUserEmail = email;   //si el usuario se registra correctamente mantiene la sesion iniciada
                } else
                    Toast.makeText(this, "Nombre de usuario ya registrado anteriormente.", Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(this,"Confirme la contraseña correctamente.",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Falta algun campo del registro por rellenar.", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Método auxiliar para comprobar que los campos para el registro de usuarios no esten vacios
     *
     * @param email
     * @param password
     * @param confirmPassword
     * @param name
     * @param surname
     * @return true en caso de que los campos esten correctamente sino devuelve false
     */
    private boolean comprobarCampos(String email, String password, String confirmPassword, String name, String surname) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || name.isEmpty() || surname.isEmpty())
            return false;
        return false;
    }
}