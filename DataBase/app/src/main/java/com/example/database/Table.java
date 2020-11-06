package com.example.database;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Table extends AppCompatActivity {

    private UsersDataBase BBDD;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);


        //Inicializamos nuestra base de datos
        BBDD = new UsersDataBase(this);

        //Inicializamos elementos de la interfaz
        dynamic = (ListView) findViewById(R.id.ContentList);
        btnBack = (Button) findViewById(R.id.btnBack);


    }

    //Metodo para volver al menu principal
    public void goBack(View view) {
        Intent i = new Intent(this, MainActivity.class);
    }


    //Metodo para buscar/enseñar el ranking

   public User rank(){
        User user = null;
        
   }

}