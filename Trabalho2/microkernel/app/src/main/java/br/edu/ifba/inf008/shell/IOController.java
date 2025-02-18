package br.edu.ifba.inf008.shell;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import br.edu.ifba.inf008.interfaces.IIOController;
import br.edu.ifba.inf008.models.Library;

public class IOController implements IIOController
{
    private static final String FILE_NAME = "libraryData.dat";
    public static <T> boolean saveData(Library data, int numberOfBooks, int numberOfUsers, int numberOfLoans){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(data);
            oos.writeInt(numberOfLoans);
            oos.writeInt(numberOfBooks);
            oos.writeInt(numberOfUsers);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Object[] loadData(){
        try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            var library = (Library) ios.readObject();
            var numberOfLoans = ios.readInt();
            var numberOfBooks = ios.readInt();
            var numberOfUsers = ios.readInt();
            return new Object[]{
                library,
                numberOfBooks,
                numberOfLoans,
                numberOfUsers
            };
        } catch (Exception e) {
            e.printStackTrace();
            
            return new Object[]{
                new Library(),
                0,
                0,
                0
            };
        }
        
    }
}
