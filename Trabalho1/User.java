package Trabalho1;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private static int numberOfUsers = 0;
    private int type;

    public User(String name, String email, String password, int type){
        id = numberOfUsers++;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }
}