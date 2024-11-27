package Trabalho1;

import java.util.ArrayList;

public class Administrator extends User{
    protected ArrayList<Product> products;
    protected ArrayList<Costumer> costumers;
    protected ArrayList<Administrator> administrators;
    public Administrator(String name, String email, String password, Type type){
        super(name, email, password, type);
    }

    public void createNewProduct(String name, String description, float price, int numberOfThisProductInStock, String category){
        Product product = new Product(name, description, price, numberOfThisProductInStock, category);
        products.add(product);
    }
    
    public void createNewUser(String name, String email, String password, Type type, String adress){
        if(type == Type.Costumer){
            Costumer costumer = new Costumer(name, email, password, type, adress);
            costumers.add(costumer);
        }else{
            Administrator administrator = new Administrator(name, email, password, type);
            administrators.add(administrator);
        }
    }

    public void moreExpensiveOrderReport(){

    }

    public void productWithLowestInventory(){

    }
}
