package Trabalho1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable{
    private int id;
    private String name;
    private String description;
    protected float price;
    protected int numberOfThisProductsInInventory;
    private String category;
    private static int numberOfProducts = 0;
    protected ArrayList<Product> products = new ArrayList<Product>();

    public Product(){
    }

    public Product(String name, String description, float price, int numberOfThisProductsInInventory, String category){
        id = numberOfProducts++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.numberOfThisProductsInInventory = numberOfThisProductsInInventory;
        this.category = category;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public Product getProduct(int index){
        return products.get(index);
    }

    public void productList(){
        for(int i = 0; i<products.size(); i++){
            Product product = products.get(i);
            System.out.println("["+product.id+"] - "+product.name+" - $"+product.price);
        }
    }

    public void productDeserialize() throws ClassNotFoundException, IOException{
        FileInputStream fis = new FileInputStream("product.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        products = (ArrayList<Product>) ois.readObject();
        fis.close();
    }

    public void productSerialize() throws IOException{
        FileOutputStream fos = new FileOutputStream("product.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(products);
        fos.close();
    }
}
