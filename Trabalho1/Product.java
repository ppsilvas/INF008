package Trabalho1;

public class Product {
    private int id;
    private String name;
    private String description;
    private float price;
    private int numberOfThisProductsInStock;
    private int numberOfProducts = 0;

    public Product(String name, String description, float price, int numberOfThisProductInStock){
        id = numberOfProducts++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.numberOfThisProductsInStock = numberOfThisProductInStock;
    }
}
