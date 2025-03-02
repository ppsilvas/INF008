package br.edu.ifba.inf008.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Loan implements Serializable {
    public static int numberOfLoans;
    private int id;
    private User user;
    private ArrayList<BooksStatus> books;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public Loan(){
        this.books = new ArrayList<>();
    }

    public Loan(User user, ArrayList<BooksStatus> books, LocalDate loanDate){
        this.id = numberOfLoans++;
        this.user = user;
        this.books = books;
        this.loanDate = loanDate;
        this.returnDate = loanDate.plusDays(14);
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<BooksStatus> getBooks() {
        return books;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setId() {
        this.id = numberOfLoans++;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBooks(Book book, boolean isLoaned) {
        BooksStatus newBook = new BooksStatus(book, isLoaned);
        books.add(newBook);
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
        this.returnDate = loanDate.plusDays(14);
    }
}
