package br.edu.ifba.inf008.models;

import java.io.Serializable;

public class Book implements Serializable {
    public static int numberOfBooks = 0;
    private int isbn;
    private String title;
    private String author;
    private int releaseYear;
    private String genre;
    private boolean available;

    public Book(){
        
    }

    public Book(String title, String author, int releaseYear, String genre){
        this.isbn = numberOfBooks++;
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.available = true;
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

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public boolean getIsAvailable(){
        return available;
    }

    public void setAvailable(boolean available){
        this.available = available;
    } 
}
