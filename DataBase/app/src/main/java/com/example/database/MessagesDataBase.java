package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MessagesDataBase extends SQLiteOpenHelper {

    //Nuestra BBDD
    private SQLiteDatabase db;

    /*Sentencia SQL de creacion de la tabla de mensajes,
    sus claves primarias son un entero que se autoincrementa a medida que se insertan en la tabla*/
    private static final String MESSAGES_TABLE_CREATE
            = "CREATE TABLE messages(_id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT)";
    //Nombre de la BBDD de mensajes
    private static final String DB_MESSAGES_NAME = "messages.sqlite";
    //Version de la BBDD
    private static final int DB_VERSION = 1;

    //Constructor del gestor de la BBDD de mensajes
    public MessagesDataBase(Context context) {
        super(context, DB_MESSAGES_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    //Creacion de la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MESSAGES_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /**
     * Inserta en la base de datos un nuevo mensaje con el texto pasado por argumento
     *
     * @param text el texto del nuevo mensaje a introducir.
     * @return false en caso de fallo en la inserción y true en caso de exito
     */
    public boolean insert(String text) {
        ContentValues cv = new ContentValues();
        cv.put("text", text);
        if (db.insert("messages", null, cv) == -1)
            return false;
        return true;
    }


    /**
     * Metodo para extraer un mensaje aleatorio de la base de datos de mensajes
     *
     * @return null en caso de que la base de datos esté vacia o un mensaje aleatorio
     * previamente guardado
     */
    public COVID_Message getMessage() {
        COVID_Message message = null;

        //Sentencia SQL para coger un mensaje aleatorio de la BBDD
        Cursor c = db.rawQuery("SELECT _id, text from messages ORDER BY RANDOM() LIMIT 1", null);
        if (c.moveToFirst()) {
            String text = c.getString(c.getColumnIndex("text"));
            int id = c.getInt(c.getColumnIndex("_id"));
            message = new COVID_Message(id, text);
        }
        return message;
    }
}
