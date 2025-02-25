package br.edu.ifba.inf008.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable {
    public static int numberOfLoans;
    private int id;
    private User user;
    private Book book;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private boolean loaned;

    public Loan(User user, Book book, LocalDate loanDate){
        this.id = numberOfLoans++;
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = loanDate.plusDays(14);
        this.loaned = true;
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
        return returnDate;
    }

    public boolean getLoaned(){
        return loaned;
    }

    public void setLoaned(){
        loaned = false;
    }

    public boolean isOverdue(){
        return returnDate.isBefore(LocalDate.now());
    }

    public double calculateFine(){
        return (LocalDate.now().toEpochDay() - returnDate.toEpochDay())*0.5;
    }
}
