import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

public class User implements Serializable{
    protected int id;
    private String name;
    private String email;
    private byte[] password;
    protected int type;
    private static int numberOfUser = 0;
    private byte[] salt;
    private static HashMap<String, User> userMap = new HashMap<String,User>();

    private User(){
        
    }

    public User(String name, String email, String password, int  type) throws InvalidKeySpecException, NoSuchAlgorithmException{
        id = numberOfUser++;
        this.name = name;
        this.email = email;
        salt = SystemUtil.generateSalt();
        this.password = SystemUtil.hashPassword(password, salt);
        this.type = type;
    }

    public static void add(String email, User user){
        userMap.putIfAbsent(email, user);
    }

    public static boolean checkEmail(String email){
        return userMap.containsKey(email);
    }

    public static void display(){
       for(User user: userMap.values()){
            System.out.println("["+user.id+"]-Name:"+user.name+"-Email"+user.email);
       }

    }

    public static User getUser(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException{
        User user = userMap.get(username);
        if(user != null && password != null){
            if(MessageDigest.isEqual(user.password, SystemUtil.hashPassword(password, user.salt))){
                return user;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static void deserialize() throws IOException, ClassNotFoundException{
        File file = new File("../user.dat");
        if(!file.exists() || file.length() == 0){
            return;
        }
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        userMap = (HashMap<String, User>) ois.readObject();
        numberOfUser = userMap.size();
        ois.close();
        fis.close();
    }

    public static void serialize() throws IOException{
        FileOutputStream fos = new FileOutputStream("../user.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(userMap);
        oos.close();
        fos.close();
    }
}
