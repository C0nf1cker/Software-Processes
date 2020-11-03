package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignActivity extends AppCompatActivity {
    private UsersDataBase ddbb;
    private EditText etName,etSurname,etEmail,etPassword,etConfirmPassword;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        //Inicializamos nuestra BBDD de usuarios
        ddbb = new UsersDataBase(this);

        //Inicializacion de elementos de la interdaz
        etEmail = (EditText) findViewById(R.id.txtEmail);
        etPassword = (EditText) findViewById(R.id.txtPassword);
        etConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        etName = (EditText) findViewById(R.id.txtName);
        etSurname = (EditText) findViewById(R.id.txtSurname);
    }

    public void goBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void sigIn(View view){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        if(comprobarCampos(email,password,confirmPassword,name,surname)){
            boolean correctSignIn = ddbb.sigIn(email,name,surname,password);
            if(correctSignIn)
                Toast.makeText(this,"Usuario registrado correctamente.",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this,"Nombre de usuario ya registrado anteriormente.",Toast.LENGTH_LONG).show();
        }
        Toast.makeText(this,"Falta algun campo del registro por rellenar.",Toast.LENGTH_LONG).show();
    }

    private boolean comprobarCampos(String email, String password, String confirmPassword, String name, String surname) {
        if(email.isEmpty()||password.isEmpty()||confirmPassword.isEmpty()||name.isEmpty()||surname.isEmpty())
            return false;
        if(confirmPassword.equals(password))
            return true;
        return false;
    }
}