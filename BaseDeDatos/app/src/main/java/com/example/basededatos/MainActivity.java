package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etCodigo, etDescripcion, etPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCodigo = (EditText) findViewById(R.id.txt_codigo);
        etDescripcion = (EditText) findViewById(R.id.txt_descripcion);
        etPrecio = (EditText) findViewById(R.id.txt_precio);
    }

    public void registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String codigo = etCodigo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String precio = etPrecio.getText().toString();
        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);
            BaseDeDatos.insert("articulos",null, registro);
            BaseDeDatos.close();
            etCodigo.setText("");
            etDescripcion.setText("");
            etPrecio.setText("");
        }else{
            Toast.makeText(this, "Falta rellenar algun campo", Toast.LENGTH_LONG);
        }
    }

    public void buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String codigo = etCodigo.getText().toString();
        if(!codigo.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("select descripcion, precio from articulos where codigo=" + codigo, null);
            if (fila.moveToFirst()){
                etDescripcion.setText(fila.getString(0));
                etPrecio.setText(fila.getString(1));
                BaseDeDatos.close();
            }else{
                Toast.makeText(this, "Codigo no encontrado", Toast.LENGTH_LONG);
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Debes introducir un codigo", Toast.LENGTH_LONG);
        }
    }

    public void eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String codigo = etCodigo.getText().toString();
        if(!codigo.isEmpty()){
            int cantidad = BaseDeDatos.delete("articulos", "codigo=" + codigo, null);
            BaseDeDatos.close();
            etCodigo.setText("");
            etDescripcion.setText("");
            etPrecio.setText("");
            if (cantidad==1){
                Toast.makeText(this, "Articulo eliminado", Toast.LENGTH_LONG);
            }else{
                Toast.makeText(this, "El articulo no existe", Toast.LENGTH_LONG);
            }
        }else{
            Toast.makeText(this, "Debes introducir un codigo", Toast.LENGTH_LONG);
        }
    }

    public void modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String codigo = etCodigo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String precio = etPrecio.getText().toString();
        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);
            int cantidad = BaseDeDatos.update("articulos", registro, "codigo=" + codigo, null);
            BaseDeDatos.close();
            etCodigo.setText("");
            etDescripcion.setText("");
            etPrecio.setText("");
            if (cantidad==1){
                Toast.makeText(this, "Articulo modificado", Toast.LENGTH_LONG);
            }else{
                Toast.makeText(this, "El articulo no existe", Toast.LENGTH_LONG);
            }
        }else{
            Toast.makeText(this, "Falta rellenar algun campo", Toast.LENGTH_LONG);
        }
    }

}