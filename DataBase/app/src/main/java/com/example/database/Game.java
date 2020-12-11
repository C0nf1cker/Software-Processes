package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.R;

import java.util.Random;

/**
 * Clase de la pantalla del juego en el que aparece un punto verde y se gana al pulsarlo.
 * Cada vez que se acierte se le suma 1 punto a la puntuación de la partida y una vez salgamos del
 * juego se añadira a la puntuación total del usuario si éste ha iniciado sesión anteriormente.
 */
public class Game extends AppCompatActivity  {

    AbsoluteLayout parent;
    Button b1;
    int level;

    //Usuario que está jugando y su email
    private User currentUser;
    private String currentUserEmail;
    //Base de datos de nuestros usuarios, necesaria para actualizar las puntuaciones
    private UsersDataBase ddbb;
    //showValue es el textview correspondiente a la puntuación que me va a ir variando en función de los clicks dados al círculo
    TextView showValue;
    // el counter es el contador de los clicks dados al círculo necesarios para contar la puntuación
    public int counter;
    //necesitamos unbooleano pressed para saber si se ha presionado en una partida el círculo ya que,
    //en caso de que si, no cuente como puntuación múltiples clicks sobre el mismo círculo del mismo nivel, sería ilógico
    public boolean pressed = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        level=1;

        //Linkamos el texto para la puntuación de la partida con su correspondiente en el layout
        showValue = (TextView) findViewById(R.id.counterValue);
        //inicializamos la BBDD
        ddbb = new UsersDataBase(this);
        //Tomamos el email del usuario logeado que nos manda el menú
        currentUserEmail = getIntent().getStringExtra("userEmail");
        //innicializamos pressed a false y counter a 0
        pressed = false;
        counter = 0;
        //coloca el círculo con id: button12 en posiciones aleatorias
        Button button = (Button) findViewById(R.id.button12);
        showRandomPosition(button);

    }

    /**
     * Acción del botón volver a inicio
     *
     * @param view
     */
    public void goMain(View view) {
        Intent main = new Intent(this, MainActivity.class);
        //Cuando salimos del juego y es un usuario loggeado tenemos que actualiar su puntuación
        if (currentUserEmail != null) {
            currentUser = ddbb.getUser(currentUserEmail);
            ddbb.updateScore(currentUser.getEmail(), counter+currentUser.getScore());
        }
        //Devolvemos al menú el email del usuario logeado para que mantenga la sesión iniciada
        main.putExtra("userEmail", this.currentUserEmail);
        startActivity(main);
        counter = 0;
        showValue.setText(Integer.toString(counter));
    }

    /**
     * Acción al pulsar el círculo que mostrará una notificación de victoria seguido
     * por un incremento del contador para las puntuaciones
     *
     * @param view
     */
    public void victoryNotification_increaseScore(View view) {
        if (!pressed) {
            Toast.makeText(this, "lo has encontrado!", Toast.LENGTH_SHORT).show();
            //en cada click de este botón el counter se incrementará
            counter++;
            showValue.setText(Integer.toString(counter));
        }
        pressed = true;
    }

    /**
     * Acción correspondiente al botón jugar de nuevo, necesario para que las puntuaciones
     * sean en función del número de partidas
     *
     * @param view
     */
    public void playAgain(View view) {
        //innicializamos pressed a false
        pressed = false;
        //coloca el círculo con id: button12 en posiciones aleatorias
        Button button = (Button) findViewById(R.id.button12);
        showRandomPosition(button);
        //necesario para mostrar la puntuación síncrona a tiempo real en la casilla textView counterValue
        showValue = (TextView) findViewById(R.id.counterValue);
    }

/*
    @Override
    public void onClick(View v) {
        String  str= v.getTag().toString();
        if (str.equals("0")){
            Toast.makeText(getApplicationContext(),"Click button0",Toast.LENGTH_SHORT).show();
        }
        else if (str.equals("1")){
            Toast.makeText(getApplicationContext(),"Click button1",Toast.LENGTH_SHORT).show();
        }
        else if (str.equals("2")){
            Toast.makeText(getApplicationContext(),"Click button2",Toast.LENGTH_SHORT).show();
        }
        else if (str.equals("3")){
            Toast.makeText(getApplicationContext(),"Click button3",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Click button4",Toast.LENGTH_SHORT).show();
        }
    }
    */


    public void showRandomPosition(Button button){
        AbsoluteLayout.LayoutParams absParams =
                (AbsoluteLayout.LayoutParams) button.getLayoutParams();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels - 200;
        int height = displaymetrics.heightPixels - 500;


        Random r = new Random();

        absParams.x = r.nextInt(width);
        absParams.y = r.nextInt(height);
        button.setLayoutParams(absParams);
    }

    public void nextLevel(View view){
        showValue = (TextView) findViewById(R.id.counterValue);
        pressed = false;

        level++;
        String[]btn_name={"Button1","Button2","Button3","Button4","Button5"};
        parent= (AbsoluteLayout) findViewById(R.id.l1_parent);
        for (int i = 0;i<5;i++){
            Button b1= new Button(Game.this);


            b1.setId(i+1);
            b1.setText(btn_name[i]);
            b1.setTag(i);
            parent.addView(b1);

            showRandomPosition(b1);
           // b1.setOnClickListener(Game.this);
            Button button = (Button) findViewById(R.id.button12);
            showRandomPosition(button);

        }

    }

}