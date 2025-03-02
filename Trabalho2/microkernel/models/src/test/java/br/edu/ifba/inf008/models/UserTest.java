package br.edu.ifba.inf008.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testBorrowBook_Success() {
        User user = new User("Pedro");
        Book book = new Book("Pequeno Principe","Antoine de Saint-Exupéry",2015,"Fantasia");

        user.borrowBook(book);

        assertEquals(1,user.getBorrowedBooks().size(),"User should have one borrowed book.");
        assertFalse(book.getIsAvailable(),"Book should not be available.");
    }

    @Test
    void testBorrowBook_LoanedBook() {
        User user = new User("Pedro");
        Book book = new Book("Pequeno Principe","Antoine de Saint-Exupéry",2015,"Fantasia");

        book.setAvailable(false);


        assertEquals(0,user.getBorrowedBooks().size(),"User should have one borrowed book.");
        assertFalse(book.getIsAvailable(),"Book should not be available.");
    }

    @Test
    void testReturnBook_Success() {
        User user = new User("Pedro");
        Book book = new Book("Pequeno Principe","Antoine de Saint-Exupéry",2015,"Fantasia");

        user.borrowBook(book);

        boolean result = user.returnBook(book);

        assertTrue(result,"There should return successful the book.");
        assertEquals(0,user.getBorrowedBooks().size(),"User should not have borrowed books.");
    }
}