import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class IO {
    public void saveData(HashMap<String, User>userMap, ArrayList<Product> productList, TreeMap<Float, Order> orderMap){
        try{
            FileOutputStream fos = new FileOutputStream("./data.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userMap);
            oos.writeObject(productList);
            oos.writeObject(orderMap);
            fos.close();
        }catch(IOException e){
            System.out.print("Error saving data");;
        }
    }

    @SuppressWarnings("unchecked")
    public void loadData(HashMap<String, User>userMap, ArrayList<Product> productList, TreeMap<Float, Order> orderMap){
        try {
            FileInputStream fis = new FileInputStream("./data.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            userMap = (HashMap<String, User>) ois.readObject();
            productList  = (ArrayList<Product>) ois.readObject();
            orderMap = (TreeMap<Float, Order>) ois.readObject();
            fis.close();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("No existing Data. Starting refresh.");
        }
    }
}
