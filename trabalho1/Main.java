import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Main{
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
        UserUI.login();
        System.exit(0);
    }
}