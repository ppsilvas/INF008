package br.edu.ifba.inf008.shell;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.edu.ifba.inf008.interfaces.ILIbraryController;
import br.edu.ifba.inf008.models.Book;
import br.edu.ifba.inf008.models.Loan;
import br.edu.ifba.inf008.models.User;

public class LibraryController implements ILIbraryController {
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
        Optional<Book> book = books.stream().filter(b->b.getIsbn() == bookIsbn).findFirst();
        Optional<User> user = users.stream().filter(u->u.getId() == userId).findFirst();

        if(book.isPresent() && user.isPresent()){
            if(book.get().getIsAvailable() && user.get().getBorrewedBooks().size()<5){
                user.get().borrowBook(book.get());
                Loan loan = new Loan(user.get(), book.get());
                loans.add(loan);
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(int userId, int bookIsbn, int loanId){
        Optional<Book> book = books.stream().filter(b->b.getIsbn() == bookIsbn).findFirst();
        Optional<User> user = users.stream().filter(u->u.getId() == userId).findFirst();
        Optional<Loan> loan = loans.stream().filter(l->l.getId() == loanId).findFirst();

        if(book.isPresent() && user.isPresent() && loan.isPresent()){
            if(loan.get().isOverdue()){
                loan.get().calculateFine();
                user.get().returnBook(book.get());
                return true;
            }
        }
        return false;
    }

    public List<Book> getAvailableBooks(){
        ArrayList<Book> availableBooks = new ArrayList<>();
        for(Book book : books){
            if(book.getIsAvailable()){
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public List<Book> getBorrowedBooks(){
        ArrayList<Book> borrowedBooks = new ArrayList<>();
        for(Book book: books){
            if(!book.getIsAvailable()){
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    public Book searchBook(String title){
        Optional<Book> book = books.stream().filter(b->b.getTitle().equals(title)).findFirst();
        if(!book.isPresent())
            return null;
        return book.get();
    }
}
