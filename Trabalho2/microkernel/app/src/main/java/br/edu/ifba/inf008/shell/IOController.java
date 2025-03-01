package br.edu.ifba.inf008.shell;

import java.io.*;
import java.util.ArrayList;

import br.edu.ifba.inf008.interfaces.IIOController;
import br.edu.ifba.inf008.models.Book;
import br.edu.ifba.inf008.models.Loan;
import br.edu.ifba.inf008.models.User;

public class IOController implements IIOController
{
    private static final String FILE_NAME = "libraryData.dat";
    @Override
    public boolean saveData(ArrayList<Book>books,ArrayList<User>users,ArrayList<Loan>loans, int numberOfBooks, int numberOfUsers, int numberOfLoans){
        File file = new File(FILE_NAME);
        if(!file.exists()){
            System.out.println("File doesn't exist");
            try {
                System.out.println("Creating File");
                file.createNewFile();
            } catch (Exception e) {
                System.err.println(e.getClass()+". Unable to create File");
            }
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(books);
            oos.writeObject(users);
            oos.writeObject(loans);
            oos.writeInt(numberOfLoans);
            oos.writeInt(numberOfBooks);
            oos.writeInt(numberOfUsers);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Object[] loadData(){
        File file = new File(FILE_NAME);
        if(!file.exists()||file.length() == 0){
            try {   
                return new Object[]{
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    0,
                    0,
                    0
                };
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ArrayList<Book>books = (ArrayList<Book>) ios.readObject();
            ArrayList<User>users = (ArrayList<User>) ios.readObject();;
            ArrayList<Loan>loans = (ArrayList<Loan>) ios.readObject();;
            int numberOfLoans = ios.readInt();
            int numberOfBooks = ios.readInt();
            int numberOfUsers = ios.readInt();
            return new Object[]{
                books,
                users,
                loans,
                numberOfBooks,
                numberOfLoans,
                numberOfUsers
            };
        } catch (Exception e) {
            System.out.println(e.getClass()+" - "+e.getMessage());
            
            return new Object[]{
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                0,
                0,
                0
            };
        }

    }
}
