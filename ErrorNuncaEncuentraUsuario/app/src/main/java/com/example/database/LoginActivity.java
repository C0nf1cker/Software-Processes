package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    public void login(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "covidDataBase", null, 1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();
        String email = etEmail.getText().toString();
        String pass = etPassword.getText().toString();
        if(!email.isEmpty()){
            //Añadir que compruebe que la contraseña introducida es la que esta asociada al correo introducido
            Cursor row = dataBase.rawQuery("select password from users where email=" + "'"+email+"'", null);
            if (row.moveToFirst()){
                Toast.makeText(this, "Usuario encontrado", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_LONG).show();
            }
            dataBase.close();
        }else{
            if (email.isEmpty()){
                Toast.makeText(this, "Debe rellenar el campo Email", Toast.LENGTH_LONG).show();
            }
            if (pass.isEmpty()){
                Toast.makeText(this, "Debe rellenar el campo Contraseña", Toast.LENGTH_LONG).show();
            }
        }
    }

}