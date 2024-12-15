package Trabalho.model;

import java.io.Serializable;
import java.security.MessageDigest;

import Trabalho.SystemUtil.hash;

public class User implements Serializable{
    private static int numberOfUsers = 0;
    private int id;
    private String fullName;
    private String email;
    private byte[] salt;
    private byte[] password;
    
    private User(){

    }

    public User(String fullName, String email, String password){
        this.id = numberOfUsers++;
        this.fullName = fullName;
        this.email = email;
        this.salt = hash.saltGenerator();
        this.password = hash.hashPassword(password, salt);
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return fullName;
    }

    public String getEmail(){
        return email;
    }

    public static int getNumberOfUsers(){
        return numberOfUsers;
    }

    public static void setNumberOfUsers(int number){
        numberOfUsers = number;
    }

    public boolean authenticate(String email, String password){
        return MessageDigest.isEqual(this.password, hash.hashPassword(password, salt));
    }
}
