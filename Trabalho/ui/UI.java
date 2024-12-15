package Trabalho.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import Trabalho.model.User;
import Trabalho.model.Product;
import Trabalho.SystemUtil.Clear;
import Trabalho.io.dataIO;
import Trabalho.model.Administrator;
import Trabalho.model.Costumer;
import Trabalho.model.Order;

public class UI {
    private static HashMap<String, User> users = new HashMap<String, User>();
    private static ArrayList<Product> products = new ArrayList<Product>();
    private static TreeMap<Float,Order> orders = new TreeMap<Float, Order>();

    public void run(){
        Scanner scanner = new Scanner(System.in);
        loadData();
        System.out.println("Press any Key...");
        scanner.nextLine();
        checkUsers();
        while(true){
            Clear.clearDisplay();
            System.out.println("E-commerce INF008");
            System.out.println("\n[1]-Login");
            System.out.println("[0]-Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 1){
                login(scanner);
            }else{
                saveData();
                System.out.println("Leaving System ...");
                scanner.close();
                return ;
            }
        }
    }

    private void login(Scanner scanner){
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
                    adminUi.run(scanner, users, orders, products,(Administrator)user);
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

    @SuppressWarnings("unchecked")
    private void loadData(){
        Object[] data = dataIO.deserialize();
        Object[] staticData = dataIO.staticDeserialize();
        users = (HashMap<String, User>) data[0];
        products = (ArrayList<Product>) data[1];
        orders = (TreeMap<Float, Order>) data[2];
        User.setNumberOfUsers((Integer)staticData[0]);
        Product.setNumberOfProducts((Integer)staticData[1]);
        Order.setNumberOfOrders((Integer)staticData[2]);
    }

    private void saveData(){
        dataIO.serealize(users, products, orders);
        dataIO.staticSerialize(User.getNumberOfUsers(), Product.getNumberOfProducts(), Order.getNumberOfOrders());
    }

    private void checkUsers(){
        if(users.isEmpty()){
            createAdmin();
        }
    }

    private void createAdmin(){
        Administrator a = new Administrator("admin", "admin", "admin");
        users.put("admin", a);
    }
}
