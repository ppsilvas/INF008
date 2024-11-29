package Trabalho1;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private static int numberOfUsers = 0;
    private Type type;
    private SystemUtil systemUtil =  new SystemUtil(); 

    public User(){

    }

    public User(String name, String email, String password, Type type){
        id = numberOfUsers++;
        this.name = name;
        this.email = email;
        this.password = systemUtil.hashPassword(password);
        this.type = type;
    }
}