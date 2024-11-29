package Trabalho1;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int id;
    private Date orderDate;
    private ArrayList<Product> BuyList = new ArrayList<Product>();
    private float totalOfOrder;
    private int idCostumer;
    private int numberOfOrder = 0;

    public Order(){
    }

    public Order(ArrayList<Product> BuyList, float totalOfOrder, int idCostumer){
        id = numberOfOrder++;
        orderDate = new Date();
        this.totalOfOrder = totalOfOrder;
        this.BuyList = BuyList;
        this.idCostumer = idCostumer;
    }
}
