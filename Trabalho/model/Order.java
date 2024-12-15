package Trabalho.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.TreeMap;

public class Order implements Serializable{
    private static int numberOfOrders = 0;
    private int id;
    private String date;
    private HashMap<Product, Integer> productList;
    private float total;

    public Order(HashMap<Product, Integer> productList, float total){
        this.id = numberOfOrders++;
        this.date = Trabalho.SystemUtil.date.handleDate();
        this.productList = new HashMap<>(productList);
        this.total = total;
    }

    public int getId(){
        return id;
    }

    public String getDate(){
        return date;
    }

    public HashMap<Product, Integer> getItems(){
        System.out.println(productList);
        return productList;
    }

    public float getTotal(){
        return total;
    }

    public static int getNumberOfOrders(){
        return numberOfOrders;
    }

    public static void setNumberOfOrders(int number){
        numberOfOrders = number;
    }

    public static Order mostExpensive(TreeMap<Float, Order> orders){
        float index = orders.lastKey();
        Order order = orders.get(index);
        return order;
    }
}
