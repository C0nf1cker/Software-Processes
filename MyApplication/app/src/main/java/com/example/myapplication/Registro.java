package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

public class Registro extends Activity {

    private TextView lblGotoLogin;
    private Button btnRegister;
    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputNacionalidad;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        inputFullName = (EditText) findViewById(R.id.txtUserName);
        inputEmail = (EditText) findViewById(R.id.txtEmail);
        inputPassword = (EditText) findViewById(R.id.txtPass);
        inputNacionalidad = (EditText) findViewById(R.id.txtNacionalidad);
        btnRegister = (Button) findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = inputFullName.getText().toString();
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                String nacionalidad = inputNacionalidad.getText().toString();
                Profile usuario = new Profile(name, email, password, nacionalidad);
            }

            //Faltaria meter el usuario en la base de datos
        });

        lblGotoLogin = (TextView) findViewById(R.id.link_to_login);
        lblGotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            //Hay que enlazarlo con la activity del login
            public void onClick(View v) {
                Intent itemintent = new Intent(Registro.this, Login.class);
                Registro.this.startActivity(itemintent);
            }
        });

    }
}
