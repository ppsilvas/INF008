package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.ITestePersistance;

public class PersistanceTeste implements ITestePersistance{
    private LibraryController libraryController = new LibraryController();
    @Override
    public boolean loanData() {
        try {
            if(!libraryController.getAvailableBooks().equals(null) && !libraryController.getBorrowedBooks().equals(null)){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean fillShelves() {
        try {
            libraryController.newBook("Java Programming", "James Gosling", 2000, "Tech");
            libraryController.newBook( "Effective Java", "Joshua Bloch", 2018, "Programming");
            libraryController.newBook( "Spring in Action", "Craig Walls", 2018, "Framework");
            libraryController.newBook(  "Head First Design Patterns", "Eric Freeman", 2004, "Software Engineering");
            libraryController.newBook( "Java Concurrency in Practice", "Brian Goetz", 2006, "Multithreading");
            libraryController.newBook( "Kotlin in Action", "Dmitry Jemerov", 2017, "Programming");
            if(!libraryController.getAvailableBooks().isEmpty()){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean subscribeUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subscribeUsers'");
    }

    @Override
    public boolean registerLoans() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerLoans'");
    }
    
}
