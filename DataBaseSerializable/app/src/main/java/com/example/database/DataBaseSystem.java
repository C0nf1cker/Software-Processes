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


    //Constructor que crea las instancias de las listas de usuarios y mensajes que almacena el sistema
    private DataBaseSystem() {
        usersList= new LinkedList<>();
        messagesList = new LinkedList<>();
    }

    public LinkedList<Message> getMessagesList() {
        return messagesList;
    }

    //Método que carga los datos del sistema en caso de que ya haya uno creado,
    //y en caso contrario crea una instancia nueva
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

    public User login(String email, String password) {
        for (User u:usersList) {
            if ((u.getEmail().equals(email)) && (u.getPassword().equals(password))) {
                return u;
            }
        }
        return null;
    }

    public boolean addUser(String name, String surname, String email, String password){
        User u = new User(name, surname, email, password);
        usersList.add(u);
        return true;
    }

    public LinkedList<User> getUsersList() {
        return usersList;
    }

    public boolean addMessage(String description){
        int id = messagesList.size();
        Message m = new Message(id, description);
        messagesList.add(m);
        return true;
    }

    public String getMessage(){
        if (messagesList.size()==0){
            return "No hay mensajes disponibles";
        }else {
            int position = (int) Math.floor(Math.random() * messagesList.size());
            Message m = messagesList.get(position);
            return m.getDescription();
        }
    }

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

    public boolean save(Context ctx){
        try {
            FileOutputStream f = ctx.openFileOutput("BaseDeDatos.obj", Context.MODE_PRIVATE);
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