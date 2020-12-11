package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase de la pantalla del juego en el que aparece un punto verde y se gana al pulsarlo.
 * Cada vez que se acierte se le suma 1 punto a la puntuación de la partida y una vez salgamos del
 * juego se añadira a la puntuación total del usuario si éste ha iniciado sesión anteriormente.
 */

public class Game extends AppCompatActivity {
    //Temporizador para llevar la cuenta del juego
    private Temporizador timer;
    //Usuario que está jugando y su email
    private User currentUser;
    private String currentUserEmail;
    //Base de datos de nuestros usuarios, necesaria para actualizar las puntuaciones
    private UsersDataBase ddbb;
    //showValue es el textview correspondiente a la puntuación que me va a ir variando en función de los clicks dados al círculo
    TextView showValue;
    // el counter es el contador de los clicks dados al círculo necesarios para contar la puntuación
    public int counter;
    private TextView timeRemining;
    private TextView recordText;
    private ArrayList<Button> noContagiados= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        //Linkamos el texto para la puntuación de la partida con su correspondiente en el layout
        showValue = (TextView) findViewById(R.id.counterValue);
        timeRemining = (TextView) findViewById(R.id.timerText);
        recordText = (TextView) findViewById(R.id.recordText);
        //inicializamos la BBDD
        ddbb = new UsersDataBase(this);
        //Tomamos el email del usuario logeado que nos manda el menú
        currentUserEmail = getIntent().getStringExtra("userEmail");
        //innicializamos counter a 0
        counter = 0;
        timer = new Temporizador(timeRemining);
        //En caso de que haya un usuario loggeado mostramos su record por pantalla, en caso
        //de ser nuevo o no estar loggeado se muestra 0
        if(this.currentUserEmail!=null){
            String usrRecord = String.valueOf(ddbb.getUser(currentUserEmail).getScore());
            recordText.setText(usrRecord);
        }else
            recordText.setText("0");
        //Empezar el juego
        timer.star();
        colocarContagiado();
    }


    public void colocarContagiado(){
        colocar(findViewById(R.id.button12));
    }

    private void colocar(Button button) {
        //Sacar los valores en los que se va a mover el elemento contagiado, que son el ancho de la
        //pantalla y el alto de la pantalla de juego
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = findViewById(R.id.button7).getLayoutParams().height;

        //Obtener una forma de tocar la ubicacion del elemento contagiado
        ConstraintLayout.LayoutParams constraintParams =
                (ConstraintLayout.LayoutParams) button.getLayoutParams();

        Random r = new Random();

        int cuadrante = r.nextInt(3);
        int bottomMargin, rightMargin, leftMargin, topMargin;
        switch (cuadrante){
            case 0:
                bottomMargin = r.nextInt(height)-400;
                rightMargin = r.nextInt(width)-400;
                constraintParams.rightMargin=rightMargin <0 ? button.getLayoutParams().width : rightMargin;
                constraintParams.bottomMargin=bottomMargin <0 ? button.getLayoutParams().height : bottomMargin;
                break;
            case 1:
                leftMargin = r.nextInt(width)-400;
                bottomMargin = r.nextInt(height)-400;
                constraintParams.leftMargin=leftMargin <0 ? button.getLayoutParams().width : leftMargin;
                constraintParams.bottomMargin=bottomMargin <0 ? button.getLayoutParams().height : bottomMargin;
                break;
            case 2:
                topMargin = r.nextInt(height)-400;
                rightMargin = r.nextInt(width)-400;
                constraintParams.topMargin=topMargin <0 ? button.getLayoutParams().height : topMargin;
                constraintParams.rightMargin=rightMargin <0 ? button.getLayoutParams().width : rightMargin;
                break;
            case 3:
                topMargin = r.nextInt(height)-400;
                leftMargin = r.nextInt(width)-400;
                constraintParams.leftMargin=leftMargin <0 ? button.getLayoutParams().width : leftMargin;
                constraintParams.topMargin=topMargin <0 ? button.getLayoutParams().height : topMargin;
                break;
        }
        button.setLayoutParams(constraintParams);
    }

    /**
     * Acción del botón volver a inicio
     *
     * @param view
     */
    public void goMain(View view) {
        Intent main = new Intent(this, MainMenu.class);
        //Cuando salimos del juego y es un usuario loggeado tenemos que actualiar su puntuación
        updateScore();
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
    public void clickOnObjetive(View view) {
        if (!timer.hasEnd()) {
            //en cada click de este botón el counter se incrementará
            counter++;
            timer.acertar();
            showValue.setText(Integer.toString(counter));
            colocarNoContagiados(counter,this);
            colocarContagiado();
        }else
            Toast.makeText(this, "El tiempo se ha acabado pulsa jugar otra vez para volver a jugar!", Toast.LENGTH_LONG).show();
    }

    private void colocarNoContagiados(int level, Context context) {
        if(!this.noContagiados.isEmpty()){
            for (Button b:
                 noContagiados) {
                ViewGroup layout = (ViewGroup) b.getParent();
                layout.removeView(b);
            }
        }
        noContagiados = new ArrayList<>();
        int dificultad = generateCharactersByDifficulty(level);
        for(int i=0; i<dificultad;i++){
            Button b = new Button(context);
            b.setId(i+1);
            b.setTag(i);
            b.setOnClickListener(this::clickOutObjetive);
            ConstraintLayout parent = (ConstraintLayout) findViewById(R.id.l1_parent).getParent();
            parent.addView(b);
            colocar(b);
            noContagiados.add(b);
        }
    }

    private int generateCharactersByDifficulty(int level) {
        Random r = new Random();
        int characterNumber = r.nextInt(level)+level;
        //Faltaria refinar un poco la generacion de personajes en funcion de la dificultad
        return characterNumber;
    }

    public void clickOutObjetive(View view){
        if (!timer.hasEnd()) {
            timer.fallar();
            showValue.setText(Integer.toString(counter));
            Toast.makeText(this, "Te has equivado, sigue intentandolo!", Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(this, "El tiempo se ha acabado pulsa jugar otra vez para volver a jugar!", Toast.LENGTH_LONG).show();
    }

    public void playAgain(View view){
        updateScore();
        if(this.currentUserEmail!=null){
            String usrRecord = String.valueOf(ddbb.getUser(currentUserEmail).getScore());
            recordText.setText(usrRecord);
        }else
            recordText.setText("0");
        timer.restart();
        showValue.setText("0");
        colocarContagiado();
        counter = 0;
    }

    private void updateScore(){
        if (currentUserEmail != null) {
            currentUser = ddbb.getUser(currentUserEmail);
            if(currentUser.getScore()<counter) {
                ddbb.updateScore(currentUser.getEmail(), counter);
                Toast.makeText(this, "Felicidades tienes un nuevo record de "+counter+"!", Toast.LENGTH_LONG).show();
            }
        }
    }
}