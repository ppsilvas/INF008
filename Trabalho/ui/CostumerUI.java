package Trabalho.ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import Trabalho.SystemUtil.Clear;
import Trabalho.model.*;

public class CostumerUI {
    public void run(Scanner scanner, ArrayList<Product> products, TreeMap<Float,Order>orders, Costumer costumer){
        while(true){
            Clear.clearDisplay();
            System.out.println("Hello, "+costumer.getName());
            System.out.println("\n[1]-Start new order");
            System.out.println("[0]-Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1->startNewOrder(scanner, products, orders, costumer);
                case 0->{
                    System.out.println("Logoff ...");
                    return;
                }
                default-> System.out.print("Invalid option. Try again");
            }
        }
    }

    private void startNewOrder(Scanner scanner, ArrayList<Product> products,TreeMap<Float,Order>orders,Costumer costumer){
        ShoppingCart shoppingCart = new ShoppingCart();
        while(true){
            Clear.clearDisplay();
            System.out.println("[1]-Add item in cart");
            System.out.println("[2]-Shopping cart view");
            System.out.println("[3]-Finish order");
            System.out.println("[0]-Return");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1->{
                    while (true) {
                        Clear.clearDisplay();
                        if(products.isEmpty()){
                            System.out.println("No product in stock.");
                            scanner.nextLine();
                            break;
                        }
                        for(Product product: products){
                            product.display();
                        }
                        System.out.print("Product ID:");
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        if(products.get(index).getStock()<=0){
                            System.out.println(products.get(index).getName()+" is out of stock.");
                            scanner.nextLine();
                        }else{
                            System.out.print("Quantity:");
                            int quantity = scanner.nextInt();
                            scanner.nextLine();
                            if(quantity>products.get(index).getStock()){
                                System.out.println("Theres is only "+products.get(index).getStock()+" "+products.get(index).getName()+" in the stock.");
                                scanner.nextLine();
                            }else{
                                addInCart(shoppingCart, products.get(index), quantity);
                            }
                        }
                        System.out.println("Continue buying?\n[1]-YES\n[0]-NO\nResponse: ");
                        int response = scanner.nextInt();
                        scanner.nextLine();
                        if(response == 0){
                            break;
                        }
                    }
                }
                case 2->{
                    showShoppingCart(shoppingCart);
                    System.out.println("Press ENTER to continue");
                    scanner.nextLine();
                }
                case 3->{
                    finishOrder(shoppingCart, orders, costumer);
                    System.out.println("Press ENTER to continue");
                    scanner.nextLine();
                }
                case 0->{
                    return;
                }
                default->{
                    System.out.println("Invalid option. Try again.");
                }
            }
        }
    }

    private void addInCart(ShoppingCart shoppingCart, Product item, int quantity){
        shoppingCart.addProduct(item, quantity);
    }

    private void showShoppingCart(ShoppingCart shoppingCart){
        shoppingCart.showCart();
    }

    private void finishOrder(ShoppingCart shoppingCart, TreeMap<Float,Order>orders, Costumer costumer){
        if(shoppingCart.getItems().isEmpty()){
            System.out.println("Cart is empty. Select an item.");
            return;
        }
        showShoppingCart(shoppingCart);
        shoppingCart.updateStock();
        float total = shoppingCart.calculateTotal();
        Order order = new Order(shoppingCart.getItems(), total);
        orders.put(total, order);
        costumer.orderAdd(order);
        System.out.println("Order finished!");
        emptyShoppingCart(shoppingCart);
    }

    private void emptyShoppingCart(ShoppingCart shoppingCart){
        if(shoppingCart.getItems().isEmpty()){
            System.out.println("Cart is already empty.");
            return;
        }
        shoppingCart.emptyCart();
    }
}
