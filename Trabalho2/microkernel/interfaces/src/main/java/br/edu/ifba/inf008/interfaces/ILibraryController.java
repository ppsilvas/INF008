package br.edu.ifba.inf008.interfaces;

import java.util.List;

import br.edu.ifba.inf008.models.Book;

public interface ILibraryController {
    public abstract void newUser(String name);
    public abstract void newBook(String title, String author, int realeaseYear, String genre);
    public abstract boolean borrowBook(int userId, int bookIsbn);
    public abstract boolean returnBook(int userId, int bookIsbn, int loanId);
    public abstract List<Book> getAvailableBooks();
    public abstract List<Book> getBorrowedBooks();
    public abstract Book searchBook(String title);
}


