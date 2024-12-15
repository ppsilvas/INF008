package Trabalho.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable{
    private static int numberOfProduct = 0;
    private int id;
    private String name;
    private String description;
    private float price;
    private int stockQuantity;
    private String category;

    public Product(){

    }

    public Product(String name, String description, float price, int stockQuantity, String category){
        this.id = numberOfProduct++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public float getPrice(){
        return price;
    }

    public static void lowestInStock(Product lowestProduct,ArrayList<Product> products){
        for(Product product: products){
            if(product.stockQuantity<lowestProduct.stockQuantity){
                lowestProduct = product;
            }
        }
        System.out.println("["+lowestProduct.id+"]-"+lowestProduct.name+"\nStock Quantity: "+lowestProduct.stockQuantity+"\nDescription: "+lowestProduct.description+"\nPrice: "+lowestProduct.price+"\nCategory: "+lowestProduct.category);
    }

    public void display(){
        System.out.println("["+id+"]-"+name+"-U$"+price);
    }
}