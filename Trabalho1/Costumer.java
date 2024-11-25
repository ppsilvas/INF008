package Trabalho1;

public class Costumer extends User{
    private String adress;

    public Costumer(String name, String email, String password, int type,String adress){
        super(name , email, password, type);
        this.adress = adress;
    }
}