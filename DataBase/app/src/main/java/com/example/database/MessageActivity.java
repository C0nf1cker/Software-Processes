package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity en el que se guardan y muestran los mensajes con temática COVID
 */
public class MessageActivity extends AppCompatActivity {

    //Email del usuario loggeado actualmente
    private String currentUserEmail;
    //Campo donde se mostrara el mensaje COVID en la pantalla
    private EditText messageText;
    private MessagesDataBase ddbb;
    private ProgressDialog progress;
    Button SaltarPantalla;
    private static String TAG = "MainActivity ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Recibimos el email del usuario loggeado desde el menu y lo guardamos
        currentUserEmail = getIntent().getStringExtra("userEmail");
        //Inicializamos la bbdd y linkamos con el layout del activity
        ddbb = new MessagesDataBase(this);
        setContentView(R.layout.activity_message);
        messageText = (EditText) findViewById(R.id.txtMessage);
        SaltarPantalla = (Button) findViewById(R.id.buttonNext);
    }

    /**
     * Acción de volver al menu principal
     *
     * @param view
     */
    public void goBack(View view) {
        Intent i = new Intent(this, MainMenu.class);
        i.putExtra("userEmail", this.currentUserEmail);
        startActivity(i);
    }

    /**
     * Metodo para enseñar un mensaje temática COVID aleatorio llamando a la base de datos
     * de los mensajes, en caso de no haber mensajes se notifica al usuario
     *
     * @param view
     */
    public void showMessage(View view) {
        messageText.setText("");
        COVID_Message messageToShow = ddbb.getMessage();
        if (messageToShow != null) {
            messageText.setText(messageToShow.getText());
        } else
            Toast.makeText(this, "No hay mensajes en la base de datos para mostrar.", Toast.LENGTH_LONG).show();
    }

    /**
     * Metodo para guardar el mensaje introducido en el campo llamando a la base de datos
     * de los mensajes, notificando al usuario el exito, fallo o falta de relleno del campo texto.
     *
     * @param view
     */
    public void saveMessage(View view) {
        String messageText = this.messageText.getText().toString();
        if (!messageText.isEmpty()) {
            //Si se ha rellenado el mensaje se guarda en la bbdd
            boolean correctInsert = ddbb.insert(messageText);
            if (correctInsert)
                Toast.makeText(this, "Mensaje insertado con exito.", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Mensaje insertado sin exito, vuelva a intentarlo.", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Rellene antes el campo de texto por favor.", Toast.LENGTH_LONG).show();
    }

    /**
     * Carga la barra y al finalizar te lleva al juego
     *
     * @param view
     */
    public void cargar(View view) {
        progress = new ProgressDialog(this);
        progress.setMessage("Cargando pantalla....");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setProgress(0);
        progress.show();

        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while (jumpTime < totalProgressTime) {
                    try {
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                        sleep(200);
                    } catch (InterruptedException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        };
        t.start();
        Intent main = new Intent(this, Game.class);
        main.putExtra("userEmail", this.currentUserEmail);
        startActivity(main);
    }

}