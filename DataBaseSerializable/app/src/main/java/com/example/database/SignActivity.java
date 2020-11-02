package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

    public boolean check(String name, String surname, String email, String password, String confirmPassword){
        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            return false;
        }
        if (!password.contentEquals(confirmPassword)){
            return false;
        }
        return true;
    }

    public void sign(View view){
        DataBaseSystem db = DataBaseSystem.getInstancia();
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        if (check(name, surname, email, password, confirmPassword)){
            db.addUser(name, surname, email, password);
            db.save(this);
        }
    }
}