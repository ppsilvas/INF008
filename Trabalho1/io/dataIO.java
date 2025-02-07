package Trabalho.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import Trabalho.model.Order;
import Trabalho.model.Product;
import Trabalho.model.User;

public class dataIO {
    public static void serealize(HashMap<String, User>users, ArrayList<Product> products, TreeMap<Float, Order>orders, int numberOfUser, int numberOfProduct, int numberOfOrder){
        try {
            FileOutputStream fos = new FileOutputStream("./Trabalho/io/data.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.writeObject(products);
            oos.writeObject(orders);
            oos.writeInt(numberOfUser);
            oos.writeInt(numberOfProduct);
            oos.writeInt(numberOfOrder);
            oos.close();
            fos.close();
            System.out.println("Saved with success");
        } catch (IOException e) {
            System.out.println("File Not Foudend");
        }
    }

    @SuppressWarnings("unchecked")
    public static Object[] deserialize(){
        try {
            FileInputStream fis = new FileInputStream("./Trabalho/io/data.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            HashMap<String, User>users = (HashMap<String, User>)ois.readObject();
            ArrayList<Product> products = (ArrayList<Product>)ois.readObject();
            TreeMap<Float, Order>orders = (TreeMap<Float, Order>)ois.readObject();
            int numberOfUser = ois.readInt();
            int numberOfProduct = ois.readInt();
            int numberOfOrder = ois.readInt();
            ois.close();
            fis.close();
            System.out.println("Loaded with success");
            return new Object[]{users,products,orders,numberOfUser,numberOfProduct,numberOfOrder};
        } catch (IOException e) {
            System.out.println("File Not Foudend or Empty");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        }
        return new Object[]{
            new HashMap<String, User>(),
            new ArrayList<Product>(),
            new TreeMap<Float, Order>(),
            0,
            0,
            0
        };
    }
}
