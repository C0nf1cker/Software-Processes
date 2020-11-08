package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UsersDataBase extends SQLiteOpenHelper {

    //Nuestra BBDD de usuarios
    private SQLiteDatabase db;

    //Sentencia de creacion de la tabla de usuarios
    private static final String USERS_TABLE_CREATE
            = "CREATE TABLE users(_email TEXT PRIMARY KEY, name TEXT, surname TEXT, password TEXT, score INTEGER)";

    //Nombre de la BBDD de usuarios
    private static final String DB_USERS_NAME = "users.sqlite";
    //Version de la BBDD
    private static final int DB_VERSION = 1;


    public UsersDataBase(Context context) {
        super(context, DB_USERS_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USERS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Metodo usado para registrar usuarios. Comprueba si existe un usuario con el mismo email
     * ya registrado y en caso contrario lo inserta en la base de datos.
     *
     * @param email
     * @param name
     * @param surname
     * @param password
     * @return true si ha habido exito insertando al usuario, false si el email ya estaba registrado
     */
    public boolean sigIn(String email, String name, String surname, String password) {
        Cursor c = db.rawQuery("SELECT _email, name, surname FROM users WHERE _email =?", new String[]{email});
        if (c.moveToFirst())
            return false;
        ContentValues cv = new ContentValues();
        String score = "0";
        cv.put("_email", email);
        cv.put("name", name);
        cv.put("surname", surname);
        cv.put("password", password);
        cv.put("score", score);
        db.insert("users", null, cv);
        return true;
    }

    /**
     * Consulta en la base de datos si existe un usuario con email y contraseña iguales a los
     * argumentos pasados devolviendolo si existiese.
     *
     * @param email
     * @param password
     * @return null si no se encuentra al usuario en la bbdd, el usuario si la consulta tiene exito
     */
    public User logIn(String email, String password) {
        User user = null;
        //Consulta en la BBDD si hay algun usuario con dicho email y contraseña
        Cursor c = db.rawQuery("SELECT _email, name, surname, password, score FROM users WHERE _email=? AND password=?", new String[]{email, password});
        if (c.moveToFirst()) {
            String uName = c.getString(c.getColumnIndex("name")),
                    uSurname = c.getString(c.getColumnIndex("surname")),
                    uEmail = c.getString(c.getColumnIndex("_email")),
                    uPassword = c.getString(c.getColumnIndex("password"));
            int uScore = Integer.parseInt(c.getString(c.getColumnIndex("score")));
            user = new User(uEmail, uName, uSurname, uPassword, uScore);
        }
        return user;
    }

    /**Metodo para actualizar la puntuacion de un usuario cuando gana una partida
     * @param email del usuario
     * @param score nueva a actualizar
     * @return false si no existe el usuario y true si existe y se ha actualizado con exito
     */
    public boolean updateScore(String email,int score){
        Cursor c = db.rawQuery("SELECT _email, name, surname, password, score FROM users WHERE _email=?", new String[]{email});
        if(!c.moveToFirst())
            return false;
        ContentValues cv = new ContentValues();
        cv.put("score",score);
        db.update("users", cv, "_email=?", new String[]{email});
        return true;
    }

    public Cursor getAllUsers() {
        return db.rawQuery("SELECT _email, name, surname, password, score FROM users",null);
    }

    protected User getUser(String email){
        User toReturn = null;
        Cursor c = db.rawQuery("SELECT _email, name, surname, password, score FROM users WHERE _email=?",new String[]{email});
        if(c.moveToFirst()){
            String uName = c.getString(c.getColumnIndex("name")),
                    uSurname = c.getString(c.getColumnIndex("surname")),
                    uPassword = c.getString(c.getColumnIndex("password"));
            int uScore = c.getInt(c.getColumnIndex("score"));
            toReturn = new User(email,uName,uSurname,uPassword,uScore);
        }
        return toReturn;
    }

}
