package br.edu.ifba.inf008.shell;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import br.edu.ifba.inf008.interfaces.ILibraryController;
import br.edu.ifba.inf008.models.Book;
import br.edu.ifba.inf008.models.Library;
import br.edu.ifba.inf008.models.Loan;
import br.edu.ifba.inf008.models.User;

public class LibraryController implements ILibraryController {
    @Override
    public boolean newUser(String name){
        User user = new User(name);
        return addUser(user);
    }

    @Override
    public void newBook(String title, String author, int realeaseYear, String genre){
        Book book = new Book(title, author, realeaseYear, genre);
        addBook(book);
    }

    private boolean addUser(User user){
        if(user != null){
            for(User userExist: Library.users){
                if(userExist.getName().equalsIgnoreCase(user.getName()))
                    return false;
            }
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
            loan.setLoaned();
            user.returnBook(book);
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
    public TreeMap<User,Book> getBorrowedBooks(){
        TreeMap<User,Book> borrowedBooks = new TreeMap<>(Comparator.comparing(User::getId));
        for(Loan loan: Library.loans){
            if(loan.getLoaned()){
                borrowedBooks.put(loan.getUser(),loan.getBook());
            }
        }
        return borrowedBooks;
    }

    @Override
    public TreeMap<Double,Book> getLateBooks(){
        TreeMap<Double,Book> lateBooks = new TreeMap<>();
        for(Loan loan: Library.loans){
            if(loan.getLoaned()&&loan.isOverdue()){
                lateBooks.put(loan.calculateFine(),loan.getBook());
            }
        }
        return lateBooks;
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
        if(!user.isPresent()){
            return null;
        }
        return user.get();
    }

    @Override
    public Double calculateFine(int loanId){
        Optional<Loan> loan = Library.loans.stream().filter((l->l.getId()==loanId)).findFirst();
        if(loan.isPresent() && loan.get().isOverdue()){
            return loan.get().calculateFine();
        }
        return 0.0;
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
