package br.edu.ifba.inf008.shell;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.edu.ifba.inf008.interfaces.ILibraryController;
import br.edu.ifba.inf008.models.Book;
import br.edu.ifba.inf008.models.Library;
import br.edu.ifba.inf008.models.Loan;
import br.edu.ifba.inf008.models.User;

public class LibraryController implements ILibraryController {
    private Library library;

    public LibraryController(){
        this.library = new Library(loadData().getBooks(),loadData().getUsers(),loadData().getLoans());
    }
    @Override
    public void newUser(String name){
        User user = new User(name);
        addUser(user);
    }
    @Override
    public void newBook(String title, String author, int realeaseYear, String genre){
        Book book = new Book(title, author, realeaseYear, genre);
        addBook(book);
    }

    private boolean addUser(User user){
        if(user != null){
            library.getUsers().add(user);;
            return true;
        }
        return false;
    }

    private boolean addBook(Book book){
        if(book != null){
            library.getBooks().add(book);
            return true;
        }
        return false;
    }
    @Override
    public boolean borrowBook(int userId, int bookIsbn){
        Book book = library.getBooks().stream().filter(b -> b.getIsbn() == bookIsbn).findFirst().orElse(null);
        User user = library.getUsers().stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
        System.out.println(library.getBooks().size());
        if(book != null && user != null && book.getIsAvailable() && user.getBorrewedBooks().size()<5){
            System.out.println(library.getBooks().size());
            user.borrowBook(book);
            Loan loan = new Loan(user, book);
            library.getLoans().add(loan);;
            return true;
        }
        return false;
    }
    @Override
    public boolean returnBook(int userId, int bookIsbn, int loanId){
        Optional<Book> book = library.getBooks().stream().filter(b->b.getIsbn() == bookIsbn).findFirst();
        Optional<User> user = library.getUsers().stream().filter(u->u.getId() == userId).findFirst();
        Optional<Loan> loan = library.getLoans().stream().filter(l->l.getId() == loanId).findFirst();

        if(book.isPresent() && user.isPresent() && loan.isPresent()){
            if(loan.get().isOverdue()){
                System.out.println("Passei Return");
                loan.get().calculateFine();
                user.get().returnBook(book.get());
                saveData();
                return true;
            }
        }
        return false;
    }
    @Override
    public List<Book> getAvailableBooks(){
        ArrayList<Book> availableBooks = new ArrayList<>();
        for(Book book : library.getBooks()){
            if(book.getIsAvailable()){
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
    @Override
    public List<Book> getBorrowedBooks(){
        ArrayList<Book> borrowedBooks = new ArrayList<>();
        for(Book book: library.getBooks()){
            if(!book.getIsAvailable()){
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }
    @Override
    public Book searchBook(String title){
        Optional<Book> book = library.getBooks().stream().filter(b->b.getTitle().equals(title)).findFirst();
        if(!book.isPresent())
            return null;
        return book.get();
    }

    private Library loadData(){
        try{
        Object[] data = Core.getInstance().getIOController().loadData();
        Library library = (Library)data[0];
        Book.numberOfBooks = (Integer)data[1];
        Loan.numberOfLoans = (Integer)data[2];
        User.numberOfUsers = (Integer)data[3];
        return library;
        }catch(Exception e){
            System.out.println("Error: " + e.getClass().getName() + " - " + e.getMessage());
            return new Library();
        }
    }
    @Override
    public void saveData(){
       
        try {
            Core.getInstance().getIOController().saveData(library, Book.numberOfBooks, User.numberOfUsers, Loan.numberOfLoans);
        } catch (Exception e) {
            System.out.println("Error: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
}
