package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.models.Book;
import br.edu.ifba.inf008.models.Loan;
import br.edu.ifba.inf008.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IOControllerTest {

    private IOController ioController;
    private final String TEST_FILE = "libraryData.dat";

    @BeforeEach
    void setup(){
        ioController = new IOController();
    }

    @AfterEach()
    void tearDown(){
        File file = new File(TEST_FILE);
        if(file.exists()){
            file.delete();
        }
    }

    @Test
    void testSaveAndLoadData(){
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("1984", "George Orwell", 1949, "Ficção"));

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Bruno Silva"));

        ArrayList<Loan> loans = new ArrayList<>();

        int numOfBooks = Book.numberOfBooks;
        int numOfUsers = User.numberOfUsers;
        int numOfLoans = Loan.numberOfLoans;

        boolean saveResult = ioController.saveData(books,users,loans,Book.numberOfBooks,User.numberOfUsers,Loan.numberOfLoans);

        assertTrue(saveResult,"Data should be save successfully");

        Object[] loadedData = ioController.loadData();

        assertNotNull(loadedData);

        assertEquals(books.size(),((ArrayList<Book>)loadedData[0]).size());
        assertEquals(users.size(),((ArrayList<User>)loadedData[1]).size());
        assertEquals(loans.size(),((ArrayList<Loan>)loadedData[2]).size());
        assertEquals(numOfBooks,loadedData[3]);
        assertEquals(numOfLoans,loadedData[4]);
        assertEquals(numOfUsers,loadedData[5]);
    }

    @Test
    void testLoadDataFromNonExistingFile(){
        File file = new File(TEST_FILE);
        if(file.exists()){
            file.delete();
        }

        Object[] loadedData = ioController.loadData();
        assertNotNull(loadedData,"The function should not return null even if file does not exist");
        assertTrue(((ArrayList<Book>)loadedData[0]).isEmpty());
        assertTrue(((ArrayList<User>)loadedData[1]).isEmpty());
        assertTrue(((ArrayList<Loan>)loadedData[2]).isEmpty());
        assertEquals(0,loadedData[3]);
        assertEquals(0,loadedData[4]);
        assertEquals(0,loadedData[5]);
    }

    @Test
    void testLoadDataFromCorruptedFile(){
        try {
            File file = new File(TEST_FILE);
            try(FileWriter writer = new FileWriter(file)){
                writer.write("Corrupted data.");
            }
            Object[] loadedData = ioController.loadData();
            assertNotNull(loadedData,"Even if the file is corrupted, the function should return default values");
            assertTrue(((ArrayList<Book>)loadedData[0]).isEmpty());
            assertTrue(((ArrayList<User>)loadedData[1]).isEmpty());
            assertTrue(((ArrayList<Loan>)loadedData[2]).isEmpty());
            assertEquals(0,loadedData[3]);
            assertEquals(0,loadedData[4]);
            assertEquals(0,loadedData[5]);
        } catch (Exception e) {
            System.err.println(e.getClass()+" - "+e.getMessage());
        }
    }
}