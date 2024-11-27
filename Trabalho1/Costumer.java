package Trabalho1;

import java.util.ArrayList;

public class Costumer extends User{
    private String adress;
    protected ArrayList<Costumer> costumers;

    public Costumer(String name, String email, String password, Type type, String adress){
        super(name , email, password, type);
        this.adress = adress;
    }

    public void addCostumer(Costumer costumer){
        costumers.add(costumer);
    }
}