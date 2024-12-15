import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

public class Costumer extends User{
    private String adress;
    private ArrayList<Order> orderHistoric;

    public Costumer() throws InvalidKeySpecException, NoSuchAlgorithmException{
        super(null, null, null, 0);
    }

    public Costumer(String name, String email, String password, int type, String adress) throws InvalidKeySpecException, NoSuchAlgorithmException{
        super(name, email, password, type);
        this.adress = adress;
        this.orderHistoric = new ArrayList<>();
    }

    public void addOrder(Order order){
        orderHistoric.add(order);
    }
}
