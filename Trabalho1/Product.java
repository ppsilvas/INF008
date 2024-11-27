package Trabalho1;

public class Product {
    private int id;
    private String name;
    private String description;
    protected float price;
    protected int numberOfThisProductsInStock;
    private String category;
    private int numberOfProducts = 0;

    public Product(String name, String description, float price, int numberOfThisProductInStock, String category){
        id = numberOfProducts++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.numberOfThisProductsInStock = numberOfThisProductInStock;
        this.category = category;
    }
}
