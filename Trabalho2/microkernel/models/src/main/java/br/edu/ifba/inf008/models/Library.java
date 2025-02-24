package br.edu.ifba.inf008.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Library implements Serializable {
    public static ArrayList<Book> books = new ArrayList<Book>();
    public static ArrayList<User> users = new ArrayList<User>();
    public static ArrayList<Loan> loans = new ArrayList<Loan>();

    public Library(){
        
    }
}
