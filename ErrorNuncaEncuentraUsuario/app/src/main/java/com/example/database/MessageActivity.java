package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Math.random;

public class MessageActivity extends AppCompatActivity {

    private EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        etMessage = (EditText) findViewById(R.id.txtMessage);
    }

    public void goBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void showMessage(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "covidDataBase", null, 1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();
        //numRandom tomara un rango de 0 al numero de filas-1
        Cursor numRows = dataBase.rawQuery("select COUNT() from messages", null);
        int numRandom = (int) (random() * (numRows.getInt(0)-1));
        String id = String.valueOf(numRandom);
        Cursor row = dataBase.rawQuery("select description from messages where id=" + "'"+id+"'", null);
        if (row.moveToFirst()){
            etMessage.setText(row.getString(0));
        }else{
            Toast.makeText(this, "Mensaje no encontrado", Toast.LENGTH_LONG).show();
        }
        dataBase.close();
    }

    public void saveMessage(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "covidDataBase", null, 1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();
        String description = etMessage.getText().toString();
        if(!description.isEmpty()){
            ContentValues record = new ContentValues();
            //Aqui seleccionamos el numero de filas de la tabla
            Cursor numRows = dataBase.rawQuery("select COUNT() from messages", null);
            record.put("id", numRows.getString(0));
            record.put("description", description);
            dataBase.insert("messages",null, record);
            dataBase.close();
            etMessage.setText("");
            Toast.makeText(this, "Se ha registrado correctamente", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Debe rellenar el campo Mensaje", Toast.LENGTH_LONG).show();
        }
    }

}