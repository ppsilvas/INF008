package Trabalho1;

import java.util.HashMap;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private static int numberOfUsers = 0;
    private Type type;
    private SystemUtil systemUtil =  new SystemUtil();
    private static HashMap<String, User> userMap = new HashMap<String,User>();

    private User(){

    }

    public User(String name, String email, String password, Type type){
        id = numberOfUsers++;
        this.name = name;
        this.email = email;
        this.password = systemUtil.hashPassword(password);
        this.type = type;
    }

    public User addHashMap(User user, String email){
        return userMap.putIfAbsent(email,user);
    }

    public static void deserialize(){

    }

    public static void serizable(){

    }
}