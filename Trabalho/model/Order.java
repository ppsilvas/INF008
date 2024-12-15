package Trabalho.model;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class Order implements Serializable{
    private static int numberOfOrder = 0;
    private int id;
    private String date;
    private Map<Product, Integer> productList;
    private float total;

    public Order(Map<Product, Integer> productList, float total){
        this.id = numberOfOrder++;
        this.date = Trabalho.SystemUtil.date.handleDate();
        this.productList = productList;
        this.total = total;
    }

    public int getId(){
        return id;
    }

    public String getDate(){
        return date;
    }

    public Map<Product, Integer> getItems(){
        return productList;
    }

    public float getTotal(){
        return total;
    }

    public static void mostExpensive(TreeMap<Float, Order> orders){
        float index = orders.lastKey();
        Order order = orders.get(index);
        System.out.println("#"+order.id+"-"+order.date+"-"+order.productList.toString()+"-Total: $"+order.total);
    }
}
