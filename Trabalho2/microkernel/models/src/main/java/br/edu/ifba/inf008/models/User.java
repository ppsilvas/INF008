package br.edu.ifba.inf008.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    public static int numberOfUsers;
    private int id;
    private String name;
    private List<Book> borrewedBooks;

    public User(){
        
    }

    public User(String name){
        this.id = numberOfUsers++;
        this.name = name;
        this.borrewedBooks = new ArrayList<Book>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public List<Book> getBorrewedBooks() {
        return borrewedBooks;
    }

    public boolean borrowBook(Book book){
        if(borrewedBooks.add(book)){
            book.changeAvailable(false);
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book){
        if(borrewedBooks.remove(book)){
            book.changeAvailable(true);
            return true;
        }
        return false;
    }
}
