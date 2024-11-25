package Trabalho1;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int id;
    private Date orderDate;
    private ArrayList<Product> BuyList;
    private float totalOfOrder;
    private int numberOfOrder = 0 ;

    public Order(ArrayList<Product> BuyList, float totalOfOrder){
        id = numberOfOrder++;
        orderDate = new Date();
        this.totalOfOrder = totalOfOrder;
        this.BuyList = BuyList;
    }
}
