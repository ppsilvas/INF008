import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Costumer extends User{
    private String adress;

    public Costumer() throws InvalidKeySpecException, NoSuchAlgorithmException{
        super(null, null, null, 0);
    }

    public Costumer(String name, String email, String password, int type, String adress) throws InvalidKeySpecException, NoSuchAlgorithmException{
        super(name, email, password, type);
        this.adress = adress;
    }
}
