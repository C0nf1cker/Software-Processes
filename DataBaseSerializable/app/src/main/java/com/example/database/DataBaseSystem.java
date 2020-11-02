package com.example.database;

import android.content.Context;

import java.io.Serializable;
import java.util.LinkedList;

//importes para serializar, probablemente sobren unos cuantos
        import java.io.BufferedWriter;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.logging.Level;
        import java.util.logging.Logger;

public class DataBaseSystem implements Serializable {
    private static DataBaseSystem instancia;
    private static final long serialVersionUID = 1L;
    private LinkedList<User> usersList;
    private LinkedList<Message> messagesList;


    //constructor que crea las instancias de las listas de usuarios y subforos que almacena el sistema
    private DataBaseSystem() {
        usersList= new LinkedList<>();
        messagesList = new LinkedList<>();
    }

    public LinkedList<Message> getMessagesList() {
        return messagesList;
    }

    //método que carga los datos del sistema en caso de que ya haya uno creado, y en caso contrario crea
    //una instancia nueva
    public static  DataBaseSystem getInstancia(){
        if(instancia==null){
            File f = new File("BaseDeDatos.obj");
            if(f.exists()){
                instancia= DataBaseSystem.load();
            }
            else
                instancia=new DataBaseSystem();
        }
        return instancia;
    }

    //método que de estar registrado el usuario y no penalizado, pone el atributo usuarioConectado a este
    //además le mostrará todas las notificaciones nuevas; en caso contrario no le permitirá iniciar sesión
    public User login(String email, String password) {
        for (User u:usersList) {
            if ((u.getEmail().equals(email)) && (u.getPassword().equals(password))) {
                return u;
            }
        }
        return null;
    }

    //método que permite crear un objeto alumno, administrador o profesor dependiendo de los datos que se le pasen,
    //además añadirá este usuario a la lista de usuarios del sistema
    public boolean addUser(String name, String surname, String email, String password){
        User u = new User(name, surname, email, password);
        usersList.add(u);
        return true;
    }

    //get que nos devuelve la lista de usuarios del sistema


    public LinkedList<User> getUsersList() {
        return usersList;
    }

    //método que permite crear un objeto subforo, además añadirá este subforo a la lista de subforos del sistema
    public boolean addMessage(int id, String description){
        Message m = new Message(id, description);
        messagesList.add(m);
        return true;
    }

    //método que carga toda la información de un fichero llamado BaseDeDatos.obj al objeto sistema
    public static DataBaseSystem load(){
        DataBaseSystem db = null;
        try {
            FileInputStream file =new FileInputStream("BaseDeDatos.obj");
            ObjectInputStream inputFile = new ObjectInputStream(file);
            db = (DataBaseSystem) inputFile.readObject();
            inputFile.close();
            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return db;
    }

    //método que guarda en un fichero llamado BaseDeDatos.obj toda la información que almacena la instancia del sistema
    public boolean save(){
        try {
            FileOutputStream f = new FileOutputStream("BaseDeDatos.obj");
            ObjectOutputStream finalFile = new ObjectOutputStream(f);
            finalFile.writeObject(this);
            finalFile.close();
            f.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }

}