package Trabalho1;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SystemUtil {
    //method to clean the display
    public final void clearDisplay(){
        final String os = System.getProperty("os.name").toLowerCase();
        try{
            if(os.contains("windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            }else{
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    public String hashPassword(String password){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            
            StringBuilder hexString = new StringBuilder();
            for(byte b:hash){
                hexString.append(String.format("%02X", 0XFF & b));
            }
            String hashed = hexString.toString();
            return hashed;
        } catch (Exception e) {
            return e.toString();
        }
        
    }
}
