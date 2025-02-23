package br.edu.ifba.inf008.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {
    private ArrayList<Book> books;
    private ArrayList<User> users;
    private ArrayList<Loan> loans;

    public Library(){}

    public Library(ArrayList<Book> books, ArrayList<User> users, ArrayList<Loan> loans){
        this.books = books;
        this.users = users;
        this.loans = loans;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
