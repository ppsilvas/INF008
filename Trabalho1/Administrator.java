package Trabalho1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Administrator extends User implements Serializable{
    protected ArrayList<Administrator> administrators = new ArrayList<Administrator>();

    public Administrator(){
        super(null, null, null, null);
    }
    public Administrator(String name, String email, String password, Type type){
        super(name, email, password, type);
    }

    public void createNewProduct(String name, String description, float price, int numberOfThisProductInInvetory, String category){
        Product product = new Product(name, description, price, numberOfThisProductInInvetory, category);
        product.addProduct(product);
    }
    
    public void createNewUser(String name, String email, String password, Type type, String adress){
        if(type == Type.Costumer){
            Costumer costumer = new Costumer(name, email, password, type, adress);
            costumer.addCostumer(costumer);
        }else{
            Administrator administrator = new Administrator(name, email, password, type);
            administrators.add(administrator);
        }
    }

    public void moreExpensiveOrderReport(){

    }

    public void productWithLowestInventory(){

    }

    public void administratorDeserialize() throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("administrator.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        administrators = (ArrayList<Administrator>) ois.readObject();
        fis.close();
    }

    public void administratorSerialize() throws IOException{
        FileOutputStream fos = new FileOutputStream("administrator.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(administrators);
        fos.close();
    }
}
