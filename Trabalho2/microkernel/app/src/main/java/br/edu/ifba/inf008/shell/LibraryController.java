package br.edu.ifba.inf008.shell;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
    public boolean newBook(String title, String author, int releaseYear, String genre){
        Book book = new Book(title, author, releaseYear, genre);
        return addBook(book);
    }

    private boolean addUser(User user){
        if(user != null){
            if(searchUser(user.getName()) == null) {
                Library.users.add(user);
                return true;
            }
        }
        return false;
    }

    public List<User> getUsers(){
        return Library.users;
    }

    private boolean addBook(Book book){
        if(book != null){
            if(searchBook(book.getTitle()) == null) {
                Library.books.add(book);
                return  true;
            }
        }
        return  false;
    }

    @Override
    public boolean loanBook(User user, Book book, LocalDate loanDate){
        if(book != null && user != null && book.getIsAvailable() && user.getBorrowedBooks().size()<5){
            user.borrowBook(book);
            Loan loan = new Loan(user, book, loanDate);
            Library.loans.add(loan);
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(User user, Book book, int loanId){
        Optional<Loan> loan = Library.loans.stream().filter(b->b.getId() == loanId).findFirst();

        if(book != null && user != null && loan.isPresent()){
            loan.get().setLoaned();
            return user.returnBook(book);
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
    public TreeMap<User,List<Book>> getBorrowedBooks(){
        TreeMap<User,List<Book>> borrowedBooks = new TreeMap<>(Comparator.comparing(User::getId));
        for(Loan loan: Library.loans){
            if(loan.getLoaned()){
                borrowedBooks.computeIfAbsent(loan.getUser(), k-> new ArrayList<>()).add(loan.getBook());
            }
        }
        return borrowedBooks;
    }

    @Override
    public TreeMap<Double,Book> getLateBooks(){
        TreeMap<Double,Book> lateBooks = new TreeMap<>();
        for(Loan loan: Library.loans){
            if(loan.getLoaned()&&loan.getReturnDate().isBefore(LocalDate.now())){
                lateBooks.put(((LocalDate.now().toEpochDay()-loan.getReturnDate().toEpochDay())*0.5),loan.getBook());
            }
        }
        return lateBooks;
    }

    @Override
    public List<Loan> getLoans() {
        return Library.loans.stream().filter(Loan::getLoaned).collect(Collectors.toList());
    }

    @Override
    public Book searchBook(String title){
        Optional<Book> book = Library.books.stream().filter(b->b.getTitle().toLowerCase().contains(title.toLowerCase())).findFirst();
        return book.orElse(null);
    }

    @Override
    public User searchUser(String name){
        Optional<User> user = Library.users.stream().filter(u-> u.getName().equalsIgnoreCase(name)).findFirst();
        return user.orElse(null);
    }

    @Override
    public Double calculateFine(int loanId){
        Optional<Loan> loan = Library.loans.stream().filter((l->l.getId()==loanId)).findFirst();
        if(loan.isPresent() && loan.get().getReturnDate().isBefore(LocalDate.now())){
            return (LocalDate.now().toEpochDay() - loan.get().getReturnDate().toEpochDay())*0.5;
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
