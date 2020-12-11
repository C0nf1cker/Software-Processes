package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {

    private String currentUserEmail;
    private UsersDataBase dataBase;
    private TextView passwordTxt,confirmPasswordTxt,nameTxt,surnameTxt,emailTxt;
    private int currentUserScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        currentUserEmail = getIntent().getStringExtra("userEmail");
        passwordTxt = (TextView) findViewById(R.id.txtPassword2);
        confirmPasswordTxt = (TextView) findViewById(R.id.txtConfirmPassword2);
        nameTxt = (TextView) findViewById(R.id.txtName2);
        surnameTxt = (TextView) findViewById(R.id.txtSurname2);
        emailTxt = (TextView) findViewById(R.id.txtEmail2);
        dataBase = new UsersDataBase(this);

        if(currentUserEmail!=null) {
            User currentUser = dataBase.getUser(currentUserEmail);
            passwordTxt.setHint(currentUser.getPassword());
            confirmPasswordTxt.setHint(currentUser.getPassword());
            nameTxt.setHint(currentUser.getName());
            surnameTxt.setHint(currentUser.getSurname());
            emailTxt.setHint(currentUser.getEmail());
            currentUserScore = dataBase.getUser(currentUserEmail).getScore();
        }else
            Toast.makeText(this,"Tiene que haber iniciado sesion previamente.",Toast.LENGTH_SHORT).show();
    }

    public void saveChanges(View view){
        if(currentUserEmail!=null) {


        }else
            Toast.makeText(this,"Tiene que haber iniciado sesion previamente.",Toast.LENGTH_SHORT).show();
    }

    public void goMain(View view){
        Intent i = new Intent(this, MainMenu.class);
        i.putExtra("userEmail", this.currentUserEmail);
        startActivity(i);
    }
}