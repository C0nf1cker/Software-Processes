package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MessageActivity extends AppCompatActivity {

    private EditText messageText;
    private MessagesDataBase ddbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ddbb = new MessagesDataBase(this);
        messageText = (EditText) findViewById(R.id.txtMessage);
        setContentView(R.layout.activity_message);
    }

    public void goBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    /**Metodo para enseñar un mensaje temática COVID aleatorio llamando a la base de datos
     * de los mensajes, en caso de no haber mensajes se notifica al usuario
     * @param view
     */
    public void showMessage(View view){
        messageText.setText("");
        COVID_Message messageToShow =ddbb.getMessage();
        if(messageToShow!=null){
            messageText.setText(messageToShow.getText());
        }else
            Toast.makeText(this,"No hay mensajes en la base de datos para mostrar.",Toast.LENGTH_LONG).show();
    }

    /**Metodo para guardar el mensaje introducido en el campo llamando a la base de datos
     * de los mensajes, notificando al usuario el exito, fallo o falta de relleno del campo texto.
     * @param view
     */
    public void saveMessage(View view){
        String messageText = this.messageText.getText().toString();
        if(!messageText.isEmpty()){
            boolean correctInsert = ddbb.insert(messageText);
            if(correctInsert)
                Toast.makeText(this,"Mensaje insertado con exito.",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this,"Mensaje insertado sin exito, vuelva a intentarlo.",Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(this,"Rellene antes el campo de texto por favor.",Toast.LENGTH_LONG).show();
    }

}