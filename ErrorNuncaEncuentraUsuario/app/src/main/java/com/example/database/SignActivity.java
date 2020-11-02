package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignActivity extends AppCompatActivity {

    private EditText etName, etSurname, etEmail, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        etName = (EditText) findViewById(R.id.txtName);
        etSurname = (EditText) findViewById(R.id.txtSurname);
        etEmail = (EditText) findViewById(R.id.txtEmail);
        etPassword = (EditText) findViewById(R.id.txtPassword);
        etConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
    }

    public void goBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void sign(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "covidDataBase", null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        if((!name.isEmpty() && !surname.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) && (password.contentEquals(confirmPassword))){
            ContentValues record = new ContentValues();
            record.put("email", email);
            record.put("name", name);
            record.put("surname", surname);
            record.put("password", password);
            record.put("confirmPassword", confirmPassword);
            int score = 0;
            record.put("score", score);
            database.insert("users",null, record);
            database.close();
            etName.setText("");
            etSurname.setText("");
            etEmail.setText("");
            etPassword.setText("");
            etConfirmPassword.setText("");
            Toast.makeText(this, "Se ha registrado correctamente", Toast.LENGTH_LONG).show();
        }else{
            if (name.isEmpty()){
                Toast.makeText(this, "Debe rellenar el campo Nombre", Toast.LENGTH_LONG).show();
            }
            if (surname.isEmpty()){
                Toast.makeText(this, "Debe rellenar el campo Apellidos", Toast.LENGTH_LONG).show();
            }
            if (email.isEmpty()){
                Toast.makeText(this, "Debe rellenar el campo Email", Toast.LENGTH_LONG).show();
            }
            if (password.isEmpty()){
                Toast.makeText(this, "Debe rellenar el campo Contraseña", Toast.LENGTH_LONG).show();
            }
            if (confirmPassword.isEmpty()){
                Toast.makeText(this, "Debe rellenar el campo Confirmar Contraseña", Toast.LENGTH_LONG).show();
            }
            if (!password.contentEquals(confirmPassword)){
                Toast.makeText(this, "Contraseña no coincide con Confirmar Contraseña", Toast.LENGTH_LONG).show();
            }
        }
    }

}