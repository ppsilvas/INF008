package br.edu.ifba.inf008.shell;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf008.models.Book;
import br.edu.ifba.inf008.models.Loan;
import br.edu.ifba.inf008.models.User;

public class LibraryController {
    List<Book> books;
    List<User> users;
    List<Loan> loans;

    public LibraryController(){
        this.books = new ArrayList<Book>();
        this.users = new ArrayList<User>();
    }

    public void newUser(String name){
        User user = new User(name);
        addUser(user);
    }

    public void newBook(String title, String author, int realeaseYear, String genre){
        Book book = new Book(title, author, realeaseYear, genre);
        addBook(book);
    }

    public boolean addUser(User user){
        if(user != null){
            users.add(user);
            return true;
        }
        return false;
    }

    public boolean addBook(Book book){
        if(book != null){
            books.add(book);
            return true;
        }
        return false;
    }

    public boolean borrowBook(int userId, int bookIsbn){
        
        return false;
    }

    public boolean returnBook(int userId, int bookIsbn){
        return true;
    }

    public List<Book> getAvailableBooks(){
        return null;
    }

    public List<Book> getBorrowedBooks(){
        return null;
    }
}
