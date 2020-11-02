package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MessageActivity extends AppCompatActivity {

    private EditText etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        etDescription = (EditText) findViewById(R.id.txtMessage);
    }

    public void goBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void addMessage(View view){
        DataBaseSystem db = DataBaseSystem.getInstancia();
        String description = etDescription.getText().toString();
        if (!description.isEmpty()){
            db.addMessage(description);
            db.save(this);
            Toast.makeText(this, "Mensaje registrado correctamente", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Error en el registro", Toast.LENGTH_LONG).show();
        }
        etDescription.setText("");
    }

    public void showMessage(View view){
        etDescription.setText("");
        DataBaseSystem db = DataBaseSystem.getInstancia();
        String description = db.getMessage();
        etDescription.setText(description);
    }
}