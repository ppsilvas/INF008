package br.edu.ifba.inf008.shell;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.edu.ifba.inf008.interfaces.ILibraryController;
import br.edu.ifba.inf008.models.Book;
import br.edu.ifba.inf008.models.Library;
import br.edu.ifba.inf008.models.Loan;
import br.edu.ifba.inf008.models.User;

public class LibraryController implements ILibraryController {
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
            Library.users.add(user);;
            return true;
        }
        return false;
    }

    private boolean addBook(Book book){
        if(book != null){
            Library.books.add(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean borrowBook(int userId, int bookIsbn, LocalDate loanDate){
        Book book = Library.books.get(bookIsbn);
        User user = Library.users.get(userId);
        if(book != null && user != null && book.getIsAvailable() && user.getBorrewedBooks().size()<5){
            user.borrowBook(book);
            Loan loan = new Loan(user, book, loanDate);
            Library.loans.add(loan);;
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(int userId, int bookIsbn, int loanId){
        Book book = Library.books.get(bookIsbn);
        User user = Library.users.get(userId);
        Loan loan = Library.loans.get(loanId);

        if(book != null && user != null && loan != null){
            if(loan.isOverdue()){
                loan.calculateFine();
            }
            user.returnBook(book);
            saveData();
            return true;
        }
        return false;
    }
    
    @Override
    public List<Book> getAvailableBooks(){
        ArrayList<Book> availableBooks = new ArrayList<>();
        for(Book book : Library.books){
            if(book.getIsAvailable()){
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    @Override
    public List<Book> getBorrowedBooks(){
        ArrayList<Book> borrowedBooks = new ArrayList<>();
        for(Book book: Library.books){
            if(!book.getIsAvailable()){
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    @Override
    public List<Book> getLateBooks(){
        ArrayList<Book> lateBooks = new ArrayList<>();
        for(Loan loan: Library.loans){
            if(loan.isOverdue()){
                lateBooks.add(loan.getBook());
            }
        }
        return lateBooks;
    }

    @Override
    public List<Double> getFine(){
        ArrayList<Double> lateLoansFine = new ArrayList<>();
        for(Loan loan: Library.loans){
            if(loan.isOverdue()){
                lateLoansFine.add(loan.calculateFine());
            }
        }
        return lateLoansFine;
    }

    @Override
    public Book searchBook(String title){
        Optional<Book> book = Library.books.stream().filter(b->b.getTitle().toLowerCase().contains(title.toLowerCase())).findFirst();
        if(!book.isPresent())
            return null;
        return book.get();
    }

    @Override
    public User searchUser(String name){
        Optional<User> user = Library.users.stream().filter(u->u.getName().toLowerCase().equals(name.toLowerCase())).findFirst();
        if(!user.isPresent())
            return null;
        return user.get();
    }

    @Override
    public void loadData(){
        try{
        Object[] data = Core.getInstance().getIOController().loadData();
        Library.books = (ArrayList<Book>)data[0];
        Library.users = (ArrayList<User>)data[1];
        Library.loans = (ArrayList<Loan>)data[2];
        Book.numberOfBooks = (Integer)data[3];
        Loan.numberOfLoans = (Integer)data[4];
        User.numberOfUsers = (Integer)data[5];
        }catch(Exception e){
            System.out.println("Error: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    @Override
    public void saveData(){
       
        try {
            Core.getInstance().getIOController().saveData(Library.books,Library.users,Library.loans, Book.numberOfBooks, User.numberOfUsers, Loan.numberOfLoans);
        } catch (Exception e) {
            System.out.println("Error: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
}
