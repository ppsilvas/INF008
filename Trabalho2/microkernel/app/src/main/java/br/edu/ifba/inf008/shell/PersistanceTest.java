package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.ILibraryController;
import br.edu.ifba.inf008.interfaces.IPersistanceTest;

public class PersistanceTest implements IPersistanceTest{
    private static ILibraryController libraryController;

    public PersistanceTest(ILibraryController _libraryController){
        libraryController = _libraryController;
    }

    @Override
    public boolean loanData() {
        try {
            if(!libraryController.getAvailableBooks().isEmpty() && !libraryController.getBorrowedBooks().isEmpty()){
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
                System.out.println("Livros adicionados com sucesso!");
                return true;
            }
            libraryController.saveData();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean subscribeUsers() {
        try {
            libraryController.newUser("Alice Silva");
            libraryController.newUser("Bruno Souza");
            libraryController.newUser("Carla Mendes");
            libraryController.newUser("Diego Ferreira");
            libraryController.newUser("Elaine Costa");
            
            if(!libraryController.getAvailableBooks().isEmpty()){
                System.out.println("Usuários adicionados com sucesso!");
                return true;
            }
            libraryController.saveData();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean registerLoans() {
        try {
            boolean loan1 = libraryController.borrowBook(0, 0);
            boolean loan2 = libraryController.borrowBook(1, 1);
            boolean loan3 = libraryController.borrowBook(2, 1);
            boolean loan4 = libraryController.borrowBook(2, 2);
            System.out.println(loan1 ? "Empréstimo 1 realizado com sucesso!" : "Empréstimo 1 falhou.");
            System.out.println(loan2 ? "Empréstimo 2 realizado com sucesso!" : "Empréstimo 2 falhou.");
            System.out.println(loan3 ? "Empréstimo 3 realizado com sucesso!" : "Empréstimo 3 falhou.");
            System.out.println(loan4 ? "Empréstimo 3 realizado com sucesso!" : "Empréstimo 3 falhou.");
            if(!libraryController.getBorrowedBooks().isEmpty()){
                return true;
            }
            libraryController.saveData();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
