package br.edu.ifba.inf008.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {
    private List<Book> books;
    private List<User> users;
    private List<Loan> loans;

    public Library(){
        this.books = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setBook(List<Book> books){
        this.books = books;
    }

    public void setUser(List<User> users){
        this.users = users;
    }

    public void setLoan(List<Loan> loans){
        this.loans = loans;
    }
}
