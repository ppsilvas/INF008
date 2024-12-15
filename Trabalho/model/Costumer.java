package Trabalho.model;

import java.util.ArrayList;

public class Costumer extends User {
    private String adress;
    private ArrayList<Order> orderHistoric;

    public Costumer(String fullName, String email, String password, String adress){
        super(fullName, email, password);
        this.adress = adress;
        this.orderHistoric = new ArrayList<>();
    }

    public void orderAdd(Order order){
        orderHistoric.add(order);
    }

    public String getAdress(){
        return adress;
    }
}
