package com.example.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class DataBase implements Serializable {
    private HashMap<String,User> users;
    private HashMap<Integer, Message> messages;

    public DataBase() {
        users = new HashMap<>();
        messages = new HashMap<>();
    }

    public boolean saveUser(User u){
        User uAux = users.get(u.getMail());
        if(uAux!=null){
            return false;
        }
        users.put(u.getMail(),u);
        return true;
    }

    public User loadUser(String mail){
        return users.get(mail);
    }

    public boolean saveMessage(Message m){
        Message mAux = messages.get(m.getId());
        if(mAux!=null){
            return false;
        }
        messages.put(m.getId(),m);
        return true;
    }

    public void saveUsers(){
        try {
            File persistenceDirectory = new File("users.datum");
            if (!persistenceDirectory.exists()) {
                persistenceDirectory.mkdir();
            }
            FileOutputStream fos = new FileOutputStream("users.datum", false);
            ObjectOutputStream objOutputStream = new ObjectOutputStream(fos);
            HashMap<String, User> tempUsers = users;
            for (HashMap.Entry<String, User> user : tempUsers.entrySet()) {
                objOutputStream.writeObject(user.getValue());
                objOutputStream.reset();
            }
            objOutputStream.close();
        } catch (IOException e) {
            System.out.println("Error when trying to save users");
        }
    }

    public void saveMessages(){
        try {
            File persistenceDirectory = new File("messages.datum");
            if (!persistenceDirectory.exists()) {
                persistenceDirectory.mkdir();
            }
            FileOutputStream fos = new FileOutputStream("messages.datum", false);
            ObjectOutputStream objOutputStream = new ObjectOutputStream(fos);
            HashMap<Integer, Message> tempMessages = messages;
            for (HashMap.Entry<Integer, Message> message : tempMessages.entrySet()) {
                objOutputStream.writeObject(message.getValue());
                objOutputStream.reset();
            }
            objOutputStream.close();
        } catch (IOException e) {
            System.out.println("Error when trying to save messages");
        }
    }

    public void loadUsers(){
        DataBase u = null;
        File f = new File("users.datum");
        if (!f.exists())
            f.mkdir();
        HashMap<String,User> users = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream obj = new ObjectInputStream(fis);
            while(fis.available()!=1){
                User user = (User) obj.readObject();
                users.put(user.getMail(),user);
            }
            obj.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.users = users;
    }

    public void loadMessages(){
        File f = new File("messages.datum");
        if (!f.exists())
            f.mkdir();
        HashMap<Integer,Message> messages = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream obj = new ObjectInputStream(fis);
            while(fis.available()!=1){
                Message message = (Message) obj.readObject();
                messages.put(message.getId(),message);
            }
            obj.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.messages = messages;
    }
}
