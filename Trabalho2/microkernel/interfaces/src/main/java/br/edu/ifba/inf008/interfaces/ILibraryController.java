package br.edu.ifba.inf008.interfaces;

import java.util.List;

import br.edu.ifba.inf008.models.Book;

public interface ILibraryController {
    void newUser(String name);
    void newBook(String title, String author, int realeaseYear, String genre);
    boolean borrowBook(int userId, int bookIsbn);
    boolean returnBook(int userId, int bookIsbn, int loanId);
    List<Book> getAvailableBooks();
    List<Book> getBorrowedBooks();
    Book searchBook(String title);
}


