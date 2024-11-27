package Trabalho1;

import java.util.Scanner;

public class UILogin {
    private Costumer costumer;
    private Administrator administrator;
    
    private Type checkCredentials(String email, String password){
        return Type.Costumer;
    }

    public void login(){
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
            
        }
        scanner.close();
    }
}
