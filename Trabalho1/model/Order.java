package Trabalho.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import Trabalho.SystemUtil.SetDate;

public class Order implements Serializable{
    private static int numberOfOrders = 0;
    private int id;
    private String date;
    private HashMap<Product, Integer> productList;
    private float total;

    public Order(HashMap<Product, Integer> productList, float total){
        this.id = numberOfOrders++;
        this.date = SetDate.handleDate();
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

    public void displayItems(){
        for(Map.Entry<Product, Integer> entry: productList.entrySet()){
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(product.getName()+"-Qty:"+quantity);
        }
    } 

    public static Order mostExpensive(TreeMap<Float, Order> orders){
        float index = orders.lastKey();
        Order order = orders.get(index);
        return order;
    }
}
