package br.edu.ifba.inf008.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import br.edu.ifba.inf008.models.Book;
import br.edu.ifba.inf008.models.Loan;
import br.edu.ifba.inf008.models.User;

public interface ILibraryController {
    public abstract boolean newUser(String name);
    public abstract boolean newBook(String title, String author, int releaseYear, String genre);
    public abstract boolean loanBook(User user, Book book, LocalDate loanDate);
    public abstract boolean returnBook(User user, Book book, int loanId);
    public abstract List<User> getUsers();
    public abstract List<Book> getAvailableBooks();
    public abstract TreeMap<User,List<Book>> getBorrowedBooks();
    public abstract TreeMap<Double,Book> getLateBooks();
    public abstract List<Loan> getLoans();
    public abstract Book searchBook(String title);
    public abstract User searchUser(String name);
    public abstract void loadData();
    public abstract void saveData();
    public abstract Double calculateFine(int loanId);
}


