package br.edu.ifba.inf008.interfaces;

import java.time.LocalDate;
import java.util.List;

import br.edu.ifba.inf008.models.Book;
import br.edu.ifba.inf008.models.User;

public interface ILibraryController {
    public abstract void newUser(String name);
    public abstract void newBook(String title, String author, int realeaseYear, String genre);
    public abstract boolean borrowBook(int userId, int bookIsbn, LocalDate loanDate);
    public abstract boolean returnBook(int userId, int bookIsbn, int loanId);
    public abstract List<Book> getAvailableBooks();
    public abstract List<Book> getBorrowedBooks();
    public abstract List<Book> getLateBooks();
    public abstract List<Double> getFine();
    public abstract Book searchBook(String title);
    public abstract User searchUser(String name);
    public abstract void loadData();
    public abstract void saveData();
}


