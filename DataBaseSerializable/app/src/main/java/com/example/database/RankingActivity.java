package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class RankingActivity extends AppCompatActivity {

    private EditText etRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        etRanking = (EditText) findViewById(R.id.txtRanking);
    }

    public void goBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void showRanking(View view){
        etRanking.setText("");
        DataBaseSystem db = DataBaseSystem.getInstancia();
        int numPlayers = 5;
        LinkedList<User> list = db.getBestPlayers(numPlayers);
        if (list==null){
            Toast.makeText(this, "No hay suficientes usuarios en el ranking", Toast.LENGTH_LONG).show();
        }else {
            String topRanking = "";
            for (User u : list) {
                topRanking = topRanking + "Usuario " + u.getName() + " Puntuacion " + u.getScore() + "\n";
            }
            etRanking.setText(topRanking);
        }
    }




}