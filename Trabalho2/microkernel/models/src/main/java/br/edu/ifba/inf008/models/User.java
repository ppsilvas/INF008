package br.edu.ifba.inf008.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    public static int numberOfUsers;
    private int id;
    private String name;
    private List<Book> borrowedBooks;

    public User(){

    }

    public User(String name){
        this.id = numberOfUsers++;
        this.name = name;
        this.borrowedBooks = new ArrayList<Book>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book){
        if(book.getIsAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
        }
    }

    public boolean returnBook(Book book){
        if(borrowedBooks.remove(book)){
            book.setAvailable(true);
            return true;
        }
        return false;
    }
}
