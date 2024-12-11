import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class Administrator extends User{
    public Administrator() throws InvalidKeySpecException, NoSuchAlgorithmException{
        super(null,null, null, 0);
    }

    public Administrator(String name, String email, String password, int type) throws InvalidKeySpecException, NoSuchAlgorithmException{
        super(name, email, password, type);
    }

    public static void createUser(String name,String email, String password, int type, String adress) throws InvalidKeySpecException, NoSuchAlgorithmException{
        if(type == 1){
            Costumer costumer = new Costumer(name, email, password, 1, adress);
            User.add(email, (Costumer)costumer);
        }else{
            Administrator administrator = new Administrator(name, email, password, 2);
            User.add(email, (Administrator)administrator);
        }
    }

    public static void createProduct(String name, String description, float price, int totalInInventory, String category){
        Product product = new Product(name, description, price, totalInInventory, category);
        Product.addInList(product);
    }

    public static void showInvetory(){
        Product.showInvetory();
    }

    public static void moreExpensiveOrder(){
        Order.mostExpensiveOrder();
    }

    public static void productWithLowestInvetory(){
        Product.getLowestInventory();
    }
}
