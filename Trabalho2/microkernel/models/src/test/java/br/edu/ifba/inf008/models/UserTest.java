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

        user.borrowBook(book);

        assertEquals(0,user.getBorrowedBooks().size(),"User should have one borrowed book.");
        assertFalse(book.getIsAvailable(),"Book should not be available.");
    }

    @Test
    void testBorrowBook_LimitReached() {
        User user = new User("Pedro");
        Book book = new Book("Pequeno Principe","Antoine de Saint-Exupéry",2015,"Fantasia");
        Book book1 = new Book("1984", "George Orwell", 1949, "Distopia");
        Book book2 = new Book("O Senhor dos Anéis", "J.R.R. Tolkien", 1954, "Fantasia");
        Book book3 = new Book("O Morro dos Ventos Uivantes", "Emily Brontë", 1847, "Romance");
        Book book4 = new Book("Cem Anos de Solidão", "Gabriel García Márquez", 1967, "Realismo Mágico");
        Book book5 = new Book("A Origem das Espécies", "Charles Darwin", 1859, "Ciência");

        user.borrowBook(book);
        user.borrowBook(book1);
        user.borrowBook(book2);
        user.borrowBook(book3);
        user.borrowBook(book4);
        user.borrowBook(book5);

        assertEquals(5,user.getBorrowedBooks().size(),"User should have one borrowed book.");
        assertTrue(book5.getIsAvailable(),"Book should be available.");
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