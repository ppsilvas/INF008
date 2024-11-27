package Trabalho1;

import java.util.ArrayList;

public class Product {
    private int id;
    private String name;
    private String description;
    protected float price;
    protected int numberOfThisProductsInStock;
    private String category;
    private int numberOfProducts = 0;
    protected ArrayList<Product> products;

    public Product(String name, String description, float price, int numberOfThisProductInStock, String category){
        id = numberOfProducts++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.numberOfThisProductsInStock = numberOfThisProductInStock;
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
}
