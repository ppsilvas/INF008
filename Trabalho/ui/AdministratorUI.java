package Trabalho.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import Trabalho.SystemUtil.Clear;
import Trabalho.model.*;

public class AdministratorUI {
    public void run(Scanner scanner, HashMap<String, User> users, TreeMap<Float,Order> orders, ArrayList<Product> products, Administrator admin){
        while(true){
            Clear.clearDisplay();
            System.out.println("Hello, "+ admin.getName());
            System.out.println("\n[1]-Create new user");
            System.out.println("[2]-Create new product");
            System.out.println("[3]-Lowest product in invertory-Report");
            System.out.println("[4]-Most expensive order-Report");
            System.out.println("[5]-Show Inventory");
            System.out.println("[6]-Show Users");
            System.out.println("[7]-Show Orders");
            System.out.println("[0]-Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1->{
                    createUser(scanner, users);
                    System.out.println("Press ENTER to continue");
                    scanner.nextLine();
                }
                case 2->{
                    createProduct(scanner, products);
                    System.out.println("Press ENTER to continue");
                    scanner.nextLine();
                }
                case 3->{
                    lowestInStock(products);
                    System.out.println("Press ENTER to continue");
                    scanner.nextLine();
                }
                case 4->{
                    mostExpensiveOrder(orders);
                    System.out.println("Press ENTER to continue");
                    scanner.nextLine();
                }
                case 5->{
                    showStock(products);
                    System.out.println("Press ENTER to continue");
                    scanner.nextLine();
                }
                case 6->{
                    showUsers(users);
                    System.out.println("Press ENTER to continue");
                    scanner.nextLine();
                }
                case 7->{
                    showOrders(orders);
                    System.out.println("Press ENTER to continue");
                    scanner.nextLine();
                }
                case 0->{
                    System.out.println("Exiting administrator menu...");
                    return ;
                }
                default->{
                    System.out.println("Invalid option. Try again.");
                    scanner.nextLine();
                }
            }
        }
    }

    private void createUser(Scanner scanner, HashMap<String, User> users){
        while(true){
            Clear.clearDisplay();
            System.out.print("Type:\n[1]-Costumer\n[2]-Administrator\nSelect the type: ");
            int type = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("E-mail: ");
            String email = scanner.nextLine();
            if(checkEmail(email, users)){
                System.out.println(email+" is already being used");
                scanner.nextLine();
            }else{
                System.out.print("Password: ");
                String password = scanner.nextLine();
                if(type == 1){
                    System.out.print("Adress: ");
                    String adress = scanner.nextLine();
                    Costumer costumer = new Costumer(name, email, password, adress);
                    users.putIfAbsent(email,(Costumer)costumer);
                    break;
                }else if(type == 2){
                    Administrator administrator = new Administrator(name, email, password);
                    users.putIfAbsent(email,(Administrator)administrator);
                    break;
                }else{
                    System.out.println("Invalid Option! Try again");
                    scanner.nextLine();
                }
            }
        }
    }

    private void createProduct(Scanner scanner, ArrayList<Product> products){
        Clear.clearDisplay();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Price: ");
        float price = scanner.nextFloat();
        scanner.nextLine();
        System.out.print("In inventory: ");
        int inInventory = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Category: ");
        String category = scanner.nextLine();
        Product product = new Product(name, description, price, inInventory, category);
        products.add(product);
    }

    private void lowestInStock(ArrayList<Product> products){
        Clear.clearDisplay();
        if(products.isEmpty()){
            System.out.println("Inventory is empty.");
        }else{
            Product lowestProduct = products.getFirst();;
            Product.lowestInStock(lowestProduct,products);
        }
    }

    private void mostExpensiveOrder(TreeMap<Float,Order> orders){
        Clear.clearDisplay();
        if(orders.isEmpty()){
            System.out.println("No Order made");
        }else{
            Order order = Order.mostExpensive(orders);
            System.out.println("#"+order.getId()+"-"+order.getDate()+"-"+order.getTotal());
            System.out.println("Product List:");
            order.displayItems();
        }
    }

    private void showUsers(HashMap<String, User>users){
        for(Map.Entry<String,User> entry: users.entrySet()){
            User user = entry.getValue();
            System.out.println("["+user.getId()+"]-"+user.getName()+"\nEmail: "+user.getEmail());
        }
    }

    private void showStock(ArrayList<Product> products){
        for(Product product: products){
            System.out.println("["+product.getId()+"]-"+product.getName()+"\nStock: "+product.getStock()+"\nPrice: "+product.getPrice());
        }
    }

    private void showOrders(TreeMap<Float, Order> orders){
        for(Map.Entry<Float,Order> entry: orders.entrySet()){
            Order order = entry.getValue();
            float total = entry.getKey();
            System.out.println("Order #"+order.getId());
            order.displayItems();
            System.out.println("Total: "+total);
        }
    }

    private boolean checkEmail(String email, HashMap<String, User> users){
        return users.containsKey(email);
    }
}
