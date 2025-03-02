package br.edu.ifba.inf008.models;

import java.io.Serializable;

public class BooksStatus implements Serializable {
    private final Book book;
    private boolean isLoaned;

    public BooksStatus(){
        this.book = new Book();
    }

    public BooksStatus(Book book, boolean isLoaned){
        this.book = book;
        this.isLoaned = isLoaned;
    }

    public Book getBook() {
        return book;
    }
    
    public boolean getIsLoaned(){
        return isLoaned;
    }

    public void setIsLoaned(boolean isLoaned){
        this.isLoaned = isLoaned;
    }
}
