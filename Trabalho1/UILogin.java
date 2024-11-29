package Trabalho1;

import java.io.IOException;
import java.util.Scanner;

public class UILogin {
    private Costumer costumer = new Costumer();
    private Administrator administrator = new Administrator();
    private Product product = new Product();
    
    private Type checkCredentials(String email, String password){
        return Type.Administrator;
    }

    public void login() throws ClassNotFoundException, IOException{
        Scanner scanner = new Scanner(System.in);
        UICostumer costumerUi = new UICostumer();
        UIAdministrator administratorUi = new UIAdministrator();
        System.out.println("Email: ");
        String email =  scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        Type credentials = checkCredentials(email, password);
        if(credentials == Type.Costumer){
            costumerUi.uiCostumer(1);;
        }else{
            administratorUi.uiAdministrator();
        }
        scanner.close();
    }
}
