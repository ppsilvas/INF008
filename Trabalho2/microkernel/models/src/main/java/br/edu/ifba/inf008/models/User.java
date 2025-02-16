package br.edu.ifba.inf008.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    public static int numberOfUsers;
    private int id;
    private String name;
    private List<Book> borrewedBooks;

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
        if(borrewedBooks.size() < 5){
            borrewedBooks.add(book);
            book.availableChange(false);
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book){
        if(borrewedBooks.remove(book)){
            book.availableChange(true);
            return true;
        }
        return false;
    }
}
