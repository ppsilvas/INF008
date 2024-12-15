package Trabalho.ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import Trabalho.model.*;

public class CostumerUI {
    public void run(Scanner scanner, ArrayList<Product> products, TreeMap<Float,Order>orders, Costumer costumer){
        System.out.println("Costumer Interface");
        System.out.println("[1]-Start new order");
        System.out.println("[0]-Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice == 1) startNewOrder(scanner, products, orders, costumer);
        return ;
    }

    private void startNewOrder(Scanner scanner, ArrayList<Product> products,TreeMap<Float,Order>orders,Costumer costumer){
        ShoppingCart shoppingCart = new ShoppingCart();
        while(true){
            System.out.println("[1]-Add item in cart");
            System.out.println("[2]-Shopping cart view");
            System.out.println("[3]-Finish order");
            System.out.println("[4]-Empty shopping cart");
            System.out.println("[5]-Show Order Historic");
            System.out.println("[0]-Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1->{
                    while (true) {
                        for(Product product: products){
                            product.display();
                        }
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        if(products.get(index).getStock()<=0){
                            System.out.println(products.get(index).getName()+" is out of stock.");
                        }else{
                            System.out.print("Quantity:");
                            int quantity = scanner.nextInt();
                            scanner.nextLine();
                            if(quantity>products.get(index).getStock()){
                                System.out.println("Theres is only "+products.get(index).getStock()+" "+products.get(index).getName()+" in the stock.");
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
                case 2->showShoppingCart(shoppingCart);
                case 3->finishOrder(shoppingCart, orders, costumer);
                case 4->emptyShoppingCart(shoppingCart);
                case 5->showOrderHistoric(costumer);
                case 0->{
                    System.out.println("Exiting costumer Menu");
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
        System.out.println("Finishing order. Cart content: "+shoppingCart.getItems());
        shoppingCart.updateStock();
        float total = shoppingCart.calculateTotal();
        Order order = new Order(shoppingCart.getItems(), total);
        order.getItems();
        orders.put(total, order);
        costumer.orderAdd(order);
        System.out.println("Order finished!");
        emptyShoppingCart(shoppingCart);
    }

    private void emptyShoppingCart(ShoppingCart shoppingCart){
        shoppingCart.emptyCart();
    }

    private void showOrderHistoric(Costumer costumer){
        for(Order order: costumer.getHistoric()){
            System.out.println("#"+order.getId()+"-"+order.getItems()+"\nTotal: "+order.getTotal());
        }
    }
}
