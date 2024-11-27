package Trabalho1;

import java.util.HashMap;
import java.util.Scanner;

public class UILogin {
    private Type checkCredentials(String username, String password){
        return Type.Costumer;
    }

    public void login(){
        Scanner scanner = new Scanner(System.in);
        UICostumer costumerUi = new UICostumer();
        UIAdministrator administratorUi = new UIAdministrator();
        System.out.println("Username: ");
        String username =  scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        Type credentials = checkCredentials(username, password);
        if(credentials == Type.Costumer){
            costumerUi.uiCostumer();;
        }else{
            
        }
        scanner.close();
    }
}
