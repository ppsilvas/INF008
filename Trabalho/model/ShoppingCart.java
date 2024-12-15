package Trabalho.model;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private HashMap<Product,Integer> items;

    public ShoppingCart(){
        items = new HashMap<Product,Integer>();
    }

    public void addProduct(Product product, int quantity){
        items.put(product, items.getOrDefault(product, 0)+ quantity);
    }

    public HashMap<Product, Integer> getItems(){
        return items;
    }

    public void showCart(){
        if(items.isEmpty())
            System.out.println("Shopping cart is empty");
        else{
            System.out.println("Shopping Cart Items:");
            for (Map.Entry<Product, Integer> entry : items.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println("[" + product.getId() + "]-Name: " + product.getName() + "\nQuantity: " + quantity);
            }
        }
    }

    public void emptyCart(){
        items.clear();
    }

    public float calculateTotal(){
        float total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()){
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total+= product.getPrice() * quantity;
        }
        return total;
    }
}
