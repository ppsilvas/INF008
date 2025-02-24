package br.edu.ifba.inf008.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
    public static int numberOfLoans;
    private int id;
    private User user;
    private Book book;
    private LocalDate loanDate;
    private LocalDate returDate;

    public Loan(User user, Book book, LocalDate loanDate){
        this.id = numberOfLoans++;
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.returDate = loanDate.plusDays(14);
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturDate() {
        return returDate;
    }

    public boolean isOverdue(){
        return returDate.isBefore(LocalDate.now());
    }

    public double calculateFine(){
        return (LocalDate.now().toEpochDay() - returDate.toEpochDay())*0.5;
    }
}
