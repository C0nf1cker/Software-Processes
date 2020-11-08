package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    private UsersDataBase ddbb;
    private User currentUser;
    private String currentUserEmail;

    private TextView userName, userSurname, userScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userName = (TextView) findViewById(R.id.userName);
        userSurname = (TextView) findViewById(R.id.userSurname);
        userScore = (TextView) findViewById(R.id.userScore);

        ddbb = new UsersDataBase(this);
        currentUserEmail = getIntent().getStringExtra("userEmail");
        if(currentUserEmail==null){
            Toast.makeText(this,"Debe iniciar sesión previamente para poder acceder a su perfil",Toast.LENGTH_LONG).show();
        }else{
            currentUser = ddbb.getUser(currentUserEmail);
            userName.setText(currentUser.getName());
            userSurname.setText(currentUser.getSurname());
            userScore.setText(String.valueOf(currentUser.getScore()));
        }
    }
    public void goMain(View view){
        Intent main = new Intent(this, MainActivity.class);
        main.putExtra("userEmail",currentUserEmail);
        startActivity(main);
    }
}