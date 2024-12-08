package Trabalho1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Costumer extends User implements Serializable{
    private String adress;

    public Costumer(){
        super(null,null, null,null);
    }

    public Costumer(String name, String email, String password, Type type, String adress){
        super(name , email, password, type);
        this.adress = adress;
    }

    public void addCostumer(Costumer costumer){
    
    }

    public void costumerDeserialize() throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("costumer.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        fis.close();
    }

    public void costumerSerialize() throws IOException{
        FileOutputStream fos = new FileOutputStream("costumer.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(costumers);
        fos.close();
    }
}