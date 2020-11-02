
package com.example.pruebadatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        etEmail = (EditText) findViewById(R.id.txtLoginEmail);
        etPassword = (EditText) findViewById(R.id.txtLoginPassword);
    }

    public void login(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "covidDataBase", null, 1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();
        String email = etEmail.getText().toString();
        String pass = etPassword.getText().toString();
        if(!email.isEmpty()){
            //Añadir que compruebe que la contraseña introducida es la que esta asociada al correo introducido
            Cursor row = dataBase.rawQuery("select surname from users where email=" + "'"+email+"'", null);
            if (row.moveToFirst()){
                Toast.makeText(this, row.getString(0), Toast.LENGTH_LONG).show();
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