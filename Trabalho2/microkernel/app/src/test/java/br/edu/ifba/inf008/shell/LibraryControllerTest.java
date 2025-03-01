package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.models.Book;
import br.edu.ifba.inf008.models.Library;
import br.edu.ifba.inf008.models.Loan;
import br.edu.ifba.inf008.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LibraryControllerTest {

    private LibraryController libraryController;

    @BeforeEach
    void setUp() {
        libraryController = new LibraryController();

        Library.books = new ArrayList<>();
        Library.users = new ArrayList<>();
        Library.loans = new ArrayList<>();
    }

    @Test
    void testNewUser_Success() {
        boolean result = libraryController.newUser("Pedro");
        assertTrue(result,"User should be successfully added.");
        assertEquals(1,Library.users.size(),"There should be one user in the list.");
    }

    @Test
    void testNewUser_Duplicate(){
        libraryController.newUser("Pedro");
        boolean result = libraryController.newUser("Pedro");
        assertFalse(result,"Duplicated users should not be added.");
        assertEquals(1,Library.users.size(),"There should be one user in the list.");
    }

    @Test
    void testNewBook_Addition() {
        libraryController.newBook("Pequeno Principe","Antoine de Saint-Exupéry",2015,"Fantasia");
        assertEquals(1,Library.books.size(),"There should be one book in the library.");
        assertEquals("Pequeno Principe",Library.books.get(0).getTitle(),"The book title is incorrect.");
    }

    @Test
    void testLoanBook_Success() {
        User user = new User("Pedro");
        Book book = new Book("Pequeno Principe","Antoine de Saint-Exupéry",2015,"Fantasia");

        Library.users.add(user);
        Library.books.add(book);

        boolean result = libraryController.loanBook(user,book, LocalDate.now());

        assertTrue(result,"Loaning should be successfully.");
        assertEquals(1,user.getBorrowedBooks().size(),"User should have one borrowed book.");
    }

    @Test
    void testBorrowBook_BookUnavailable() {
        User user = new User("Pedro");
        Book book = new Book("Pequeno Principe","Antoine de Saint-Exupéry",2015,"Fantasia");
        book.setAvailable(false);

        Library.users.add(user);
        Library.books.add(book);

        boolean result = libraryController.loanBook(user,book, LocalDate.now());

        assertFalse(result,"Loaning should not be possible for an unavailable book.");
    }

    @Test
    void testReturnBook_Success() {
        User user = new User("Pedro");
        Book book = new Book("Pequeno Principe","Antoine de Saint-Exupéry",2015,"Fantasia");
        Loan loan = new Loan(user,book, LocalDate.now());
        user.borrowBook(book);

        Library.users.add(user);
        Library.books.add(book);
        Library.loans.add(loan);

        boolean result = libraryController.returnBook(user,book,loan.getId());

        assertTrue(result,"Returning the book should be successful.");
        assertTrue(book.getIsAvailable(),"The book should be available again.");
    }

    @Test
    void testSearchBook_Found() {
        libraryController.newBook("Pequeno Principe","Antoine de Saint-Exupéry",2015,"Fantasia");

        Book foundBook = libraryController.searchBook("Pequeno Principe");

        assertNotNull(foundBook,"Some nonexistent book.");
        assertEquals("Pequeno Principe", foundBook.getTitle(),"Incorrect book title.");
    }

    @Test
    void testSearchBook_NotFound() {
        Book foundBook = libraryController.searchBook("Hello World");
        assertNull(foundBook,"No book should be found");
    }

    @Test
    void testSearchUser_Found() {
        libraryController.newUser("Pedro");

        User foundUser = libraryController.searchUser("Pedro");

        assertNotNull(foundUser,"Some nonexistent user.");
        assertEquals("Pedro",foundUser.getName(),"Incorrect user name.");
    }

    @Test
    void testSearchUser_NotFound() {
        User foundUser = libraryController.searchUser("Hello World");
        assertNull(foundUser,"No user should be found");
    }

    @Test
    void testCalculateFine_NoFine() {
        User user = new User("Pedro");
        Book book = new Book("Pequeno Principe","Antoine de Saint-Exupéry",2015,"Fantasia");
        Loan loan = new Loan(user,book,LocalDate.now());

        Library.loans.add(loan);

        Double fine = libraryController.calculateFine(loan.getId());

        assertEquals(0.0,fine,"Fine should be 0 for non-overdue books");
    }

    @Test
    void testCalculateFine_WithFine() {
        User user = new User("Pedro");
        Book book = new Book("Pequeno Principe","Antoine de Saint-Exupéry",2015,"Fantasia");
        Loan loan = new Loan(user,book,LocalDate.now().minusDays(15));

        Library.loans.add(loan);

        Double fine = libraryController.calculateFine(loan.getId());

        assertEquals(0.5,fine,"Fine should be 0.5");
    }
}