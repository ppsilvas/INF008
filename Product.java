import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Product implements Serializable {
    protected int id;
    protected String name;
    private String description;
    protected float price;
    private int totalInInventory;
    private String category;
    private static int numberOfProduct = 0;
    protected static ArrayList<Product> productList = new ArrayList<Product>();

    public Product(){

    }

    public Product(String name, String description, float price, int totalInInventory, String category){
        id = numberOfProduct++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.totalInInventory = totalInInventory;
        this.category = category;
    }

    public static void productBought(int index){
        productList.get(index).totalInInventory--;
    }

    public static void addInList(Product product){
        productList.add(product);
    }

    public boolean verifyProductInventory(){
        if(totalInInventory <= 0){
            return false;
        }
        return true;
    }

    public void display(){
        System.out.println("["+id+"]-"+name+"-$:"+price);
    }

    public static void getLowestInventory(){
        if(productList.isEmpty()){
            System.out.println("No product in inventory.");
            return;
        }
        Product lowestInventory = productList.getFirst();
        for(Product atuaProduct : productList){
            if(lowestInventory.totalInInventory > atuaProduct.totalInInventory){
                lowestInventory = atuaProduct;
            }
        }
        System.out.println("["+lowestInventory.id+"] -"+lowestInventory.name+"- total:"+lowestInventory.totalInInventory);
    }

    public static void showInvetory(){
        if(productList.isEmpty()){
            System.out.println("No such product in inventory.");
            return;
        }
        for(Product currentProduct: productList){
            System.out.println("["+currentProduct.id+"] - Name: "+currentProduct.name+" - Inventory: "+currentProduct.totalInInventory);
        }
    }

    @SuppressWarnings("unchecked")
    public static void deserialize() throws IOException, ClassNotFoundException{
        File file = new File("product.dat");
        if(!file.exists() || file.length() == 0){
            return;
        }
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        productList = (ArrayList<Product>) ois.readObject();
        numberOfProduct = productList.size();
        fis.close();
    }

    public static void serialize() throws IOException{
        FileOutputStream fos = new FileOutputStream("product.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(productList);
        fos.close();
    }
}
