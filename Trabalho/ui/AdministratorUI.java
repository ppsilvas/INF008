package Trabalho.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import Trabalho.model.*;

public class AdministratorUI {
    public void run(Scanner scanner, HashMap<String, User> users, TreeMap<Float,Order> orders, ArrayList<Product> products){
        while(true){
            System.out.println("Administrator Interface");
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
                case 1:
                    createUser(scanner, users);
                    break;
                case 2:
                    createProduct(scanner, products);
                    break;
                case 3:
                    lowestInStock(products);
                    break;
                case 4:
                    mostExpensiveOrder(orders);
                    break;
                case 5:
                    showStock(products);
                    break;
                case 6:
                    showUsers(users);
                    break;
                case 7:
                    showOrders(orders);
                    break;
                case 0:
                    System.out.println("Exiting administrator menu...");
                    return ;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }

    private void createUser(Scanner scanner, HashMap<String, User> users){
        while(true){
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("E-mail: ");
            String email = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            System.out.print("Type:\n[1]-Costumer\n[2]-Administrator\nSelect the type: ");
            int type = scanner.nextInt();
            scanner.nextLine();
            if(type == 1){
                System.out.print("Adress: ");
                String adress = scanner.nextLine();
                Costumer costumer = new Costumer(name, email, password, adress);
                User response = users.putIfAbsent(email,(Costumer)costumer);
                if(response == null){
                    return;
                }else{
                    System.out.println(email+" is already being used");
                }
            }else if(type == 2){
                Administrator administrator = new Administrator(name, email, password);
                User response = users.putIfAbsent(email,(Administrator)administrator);
                if(response == null){
                    return;
                }else{
                    System.out.println(email+" is already being used");
                }
            }else{
                System.out.println("Invalid Option! Try again");
            }
        }
    }

    private void createProduct(Scanner scanner, ArrayList<Product> products){
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
        if(products.isEmpty()){
            System.out.println("Inventory is empty.");
        }else{
            Product lowestProduct = products.getFirst();;
            Product.lowestInStock(lowestProduct,products);
        }
    }

    private void mostExpensiveOrder(TreeMap<Float,Order> orders){
        if(orders.isEmpty()){
            System.out.println("No Order made");
        }else{
            Order order = Order.mostExpensive(orders);
            HashMap<Product,Integer> items = order.getItems();
            System.out.println("#"+order.getId()+"-"+order.getDate()+"-"+order.getTotal()+"\nProduct List"+items);
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
            product.display();
        }
    }

    private void showOrders(TreeMap<Float, Order> orders){
        for(Map.Entry<Float,Order> entry: orders.entrySet()){
            Order order = entry.getValue();
            System.out.println("["+order.getId()+"]-"+order.getItems()+"\nTotal: "+order.getTotal());
        }
    }
}
