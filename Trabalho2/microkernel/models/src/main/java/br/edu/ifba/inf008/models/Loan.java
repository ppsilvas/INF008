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
        this.returDate = LocalDate.now().plusDays(14);
    }

    public int getId() {
        return id;
    }

    public User getUserId() {
        return user;
    }

    public Book getBookId() {
        return book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturDate() {
        return returDate;
    }

    public boolean isOverdue(){
        return LocalDate.now().isAfter(returDate);
    }

    public long calculateFine(){
        if(isOverdue())
            return (LocalDate.now().toEpochDay() - returDate.toEpochDay());
        else
            return 0;
    }
}
