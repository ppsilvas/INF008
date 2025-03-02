package br.edu.ifba.inf008.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    @Test
    void testGettersAndSetters(){
        User user = new User("Pedro");
        Book book = new Book("Pequeno Principe","Antoine de Saint-Exupéry",2015,"Fantasia");
        ArrayList<BooksStatus> loanedBooks = new ArrayList<>();
        loanedBooks.add(new BooksStatus(book, true));

        Loan loan = new Loan(user, loanedBooks, LocalDate.now());
        
        assertEquals(loanedBooks.size(),loan.getBooks().size(),"Should have a book registered");
        assertEquals(user, loan.getUser(),"Should have a user registered");
        assertEquals(LocalDate.now(),loan.getLoanDate(),"Dates should be equal");
        assertEquals(loan.getLoanDate().plusDays(14),loan.getReturnDate(),"Dates should be equal");
    }
}