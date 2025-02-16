package br.edu.ifba.inf008.interfaces;

import java.util.List;

import br.edu.ifba.inf008.shell.models.Book;
import br.edu.ifba.inf008.shell.models.User;

public interface ILIbraryController {
    void newUser(String name);
    void newBook(String title, String author, int realeaseYear, String genre);
    boolean addUser(User user);
    boolean addBook(Book book);
    boolean borrowBook(int userId, int bookIsbn);
    boolean returnBook(int userId, int bookIsbn);
    List<Book> getAvailableBooks();
    List<Book> getBorrowedBooks();
}
