package com.example.database;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

public class Table extends AppCompatActivity {

    private UsersDataBase BBDD;
    private SQLiteDatabase db;
    private ListView dynamic;
    private LinkedList<User> users = new LinkedList<>();
    private LinkedList<String> infoToShow = new LinkedList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);


        //Inicializamos nuestra base de datos
        BBDD = new UsersDataBase(this);

        //Inicializamos elementos de la interfaz
        dynamic = (ListView) findViewById(R.id.rankList);
        rank();

    }

    //Metodo para volver al menu principal
    public void goBack(View view) {
        Intent i = new Intent(this, MainActivity.class);
    }


    /**
     * Metodo para cargar la info de los jugadores y mostrarla en la tabla
     */
    public void rank() {
        Cursor c = db.rawQuery("SELECT name, score FROM users", null);
        User userAux;
        String name, surname, email, password;
        int score;
        if (c.moveToFirst()) {
            do {
                name = c.getString(c.getColumnIndex("name"));
                surname = c.getString(c.getColumnIndex("surname"));
                email = c.getString(c.getColumnIndex("email"));
                password = c.getString(c.getColumnIndex("password"));
                score = c.getInt(c.getColumnIndex("score"));
                userAux = new User(email, name, surname, password, score);
                users.add(userAux);
            } while (c.moveToNext());
        }
        sort(users);
        for (User u : users) {
            infoToShow.add(u.getName() + "\tScore:" + u.getScore());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_ranking, infoToShow);
        dynamic.setAdapter(adapter);
    }

    /**
     * Metodo auxiliar para ordenar los usuarios de mayor a menor respecto a sus puntuaciones
     *
     * @param users
     */
    private void sort(LinkedList<User> users) {
        quicksort(users, 0, users.size() - 1);
    }

    /**
     * Metodo auxiliar para ordenar mediante el algoritmo quicksort
     *
     * @param A   es el array a ordenar
     * @param izq es el inicio del array a ordenar
     * @param der es el fin del array a ordenar
     */
    private static void quicksort(LinkedList<User> A, int izq, int der) {

        User pivote = A.get(izq); // tomamos primer elemento como pivote
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        User aux;

        while (i < j) {                          // mientras no se crucen las búsquedas
            while (A.get(i).getScore() <= pivote.getScore() && i < j)
                i++; // busca elemento mayor que pivote
            while (A.get(j).getScore() > pivote.getScore())
                j--;           // busca elemento menor que pivote
            if (i < j) {                        // si no se han cruzado
                aux = A.get(i);                      // los intercambia
                A.set(i, A.get(j));
                A.set(j, aux);
            }
        }

        A.set(izq, A.get(j));      // se coloca el pivote en su lugar de forma que tendremos
        A.set(j, pivote);      // los menores a su izquierda y los mayores a su derecha

        if (izq < j - 1)
            quicksort(A, izq, j - 1);          // ordenamos subarray izquierdo
        if (j + 1 < der)
            quicksort(A, j + 1, der);          // ordenamos subarray derecho

    }

}