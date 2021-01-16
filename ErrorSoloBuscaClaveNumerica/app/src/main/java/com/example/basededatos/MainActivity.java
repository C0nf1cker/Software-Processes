package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etConfirmPassword, etName, etSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmail = (EditText) findViewById(R.id.txtEmail);
        etPassword = (EditText) findViewById(R.id.txtPassword);
        etConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        etName = (EditText) findViewById(R.id.txtName);
        etSurname = (EditText) findViewById(R.id.txtSurname);
    }

    public void registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        if(!email.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("email", email);
            registro.put("name", name);
            registro.put("surname", surname);
            registro.put("password", password);
            int score = 0;
            registro.put("score", score);
            BaseDeDatos.insert("articulos",null, registro);
            BaseDeDatos.close();
            etEmail.setText("");
            etPassword.setText("");
            etConfirmPassword.setText("");
            etName.setText("");
            etSurname.setText("");
            Toast.makeText(this, "Producto registrado", Toast.LENGTH_LONG);
        }else{
            Toast.makeText(this, "Falta rellenar algun campo", Toast.LENGTH_LONG);
        }
    }

    public void buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String email = etEmail.getText().toString();
        if(!email.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("select name, surname from articulos where email=" + email, null);
            if (fila.moveToFirst()){
                etName.setText(fila.getString(0));
                etSurname.setText(fila.getString(1));
                BaseDeDatos.close();
            }else{
                Toast.makeText(this, "Codigo no encontrado", Toast.LENGTH_LONG);
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Debes introducir un codigo", Toast.LENGTH_LONG);
        }
    }



}