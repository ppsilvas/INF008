package Trabalho.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import Trabalho.model.User;
import Trabalho.model.Product;
import Trabalho.SystemUtil.Clear;
import Trabalho.model.Administrator;
import Trabalho.model.Costumer;
import Trabalho.model.Order;

public class UI {

    public void run(HashMap<String, User> users, ArrayList<Product> products, TreeMap<Float,Order> orders){
        while(true){
            Scanner scanner = new Scanner(System.in);
            Clear.clearDisplay();
            System.out.println("E-commerce INF008");
            System.out.println("\n[1]-Login");
            System.out.println("[0]-Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 1){
                login(scanner, users, products, orders);
            }else{
                System.out.println("Leaving System ...");
                scanner.close();
                return ;
            }
        }
    }

    private void login(Scanner scanner, HashMap<String, User> users, ArrayList<Product> products, TreeMap<Float,Order> orders){
        while (true) {
            Clear.clearDisplay();
            System.out.print("Email:");
            String email = scanner.nextLine();
            System.out.print("Password:");
            String password = scanner.nextLine();

            User user = users.get(email);

            if(user != null && user.authenticate(email, password)){
                if(user instanceof Administrator){
                    AdministratorUI adminUi = new AdministratorUI();
                    adminUi.run(scanner, users, orders, products);
                    return;
                }else if(user instanceof Costumer){
                    CostumerUI costUI = new CostumerUI();
                    costUI.run(scanner, products, orders,(Costumer)user);
                    return;
                }else{
                    System.out.println("Wrong email or password");
                    System.out.println("Press ENTER to continue");
                    scanner.nextLine();
                }
            }else{
                System.out.println("Wrong email or password");
                System.out.println("Press ENTER to continue");
                scanner.nextLine();
            }
        }
    }
}
