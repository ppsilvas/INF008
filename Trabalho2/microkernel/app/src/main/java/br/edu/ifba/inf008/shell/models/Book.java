package br.edu.ifba.inf008.shell.models;

import java.io.Serializable;

public class Book implements Serializable {
    public static int numberOfBooks = 0;
    private int isbn;
    private String title;
    private String author;
    private int realeaseYear;
    private String genre;
    private boolean isAvailabe;

    public Book(String title, String author, int realeaseYear, String genre){
        this.isbn = numberOfBooks++;
        this.title = title;
        this.author = author;
        this.realeaseYear = realeaseYear;
        this.genre = genre;
        this.isAvailabe = true;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public int getRealeaseYear() {
        return realeaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public boolean getIsAvailable(){
        return isAvailabe;
    }

    public void availableChange(boolean availabe){
        this.isAvailabe = availabe;
    } 
}
