package Trabalho1;

import java.io.IOException;
import java.util.Scanner;

public class UIAdministrator {
    SystemUtil systemUtil = new SystemUtil();
    Administrator administrator = new Administrator();
    Scanner scanner = new Scanner(System.in); 
      
    public void uiAdministrator() throws IOException{
        systemUtil.clearDisplay();
        System.out.println("[1]-Create New User: ");
        System.out.println("[2]-Create New Product: ");
        System.out.println("[3]-More Expensive Product Report: ");
        System.out.println("[4]-Product with Lowest Inventory Report: ");
        System.out.println("[0]-Exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                createNewUser();
                break;
            case 2:
                createNewProduct();
                break;
            case 3:
                moreExpensiveOrderReport();
                break;
            case 4:
                productWithLowestInventory();
                break;
            case 0:
                handleLogout();
                break;
            default:
                break;
        }
    }

    private void handleLogout() throws IOException{
        administrator.administratorSerialize();
    }

    private void createNewUser() throws IOException{
        systemUtil.clearDisplay();
        int response = 1;
        while(response == 1){
            System.out.println("User's name: ");
            String name = scanner.next();
            System.out.println("User's email: ");
            String email = scanner.next();
            System.out.println("User's password: ");
            String password = scanner.next();
            System.out.println("User's type\n[1]-Costumer\n[2]-Administrator");
            int choice = scanner.nextInt();

            if(choice == 1){
                System.out.println("User's adress: ");
                String adress = scanner.nextLine();
                administrator.createNewUser(name, email, password, Type.Costumer, adress);
            }else if(choice == 2){
                administrator.createNewUser(name, email, password, Type.Administrator, "");
            }
            
            System.out.println("Do you want to create another User?\n[1]-Yes\n[0]-No");
            response = scanner.nextInt();
        }
        uiAdministrator();
    }

    private void createNewProduct(){
        systemUtil.clearDisplay();
        int response = 1;
        while(response == 1){
            System.out.println("Product's name: ");
            String name = scanner.nextLine();
            System.out.println("Product's description: ");
            String description = scanner.nextLine();
            System.out.println("Product's price:");
            float price = scanner.nextFloat();
            System.out.println("Total in the Inventory:");
            int totalInInventory = scanner.nextInt();
            System.out.println("Product's category: ");
            String category = scanner.nextLine();

            administrator.createNewProduct(name, description, price, totalInInventory, category);

            System.out.println("Do you want to create another User?\n[1]-YES\n[0]-NO\n");
            response = scanner.nextInt();
        }
    }

    private void moreExpensiveOrderReport(){

    }

    private void productWithLowestInventory(){

    }

}
