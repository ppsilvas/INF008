package Trabalho1;

import java.util.Scanner;

public class UICostumer {
    private Scanner scanner = new Scanner(System.in);
    private SystemUtil systemUtil = new SystemUtil();
    private ShoppingCart shoppingCart = new ShoppingCart();

    public void uiCostumer(){
        systemUtil.clearDisplay();
        System.out.println("[1]-Start New Order");
        System.out.println("[0]-Exit");
        int choice = scanner.nextInt();
        if(choice == 1){
            startNewOrder();
        }else{
            handleLogout();
        }
    }

    private void handleLogout(){

    }

    private void startNewOrder(){
        systemUtil.clearDisplay();
        System.out.println("[1]-Add product");
        System.out.println("[2]-View shopping cart");
        System.out.println("[3]-Finish order");
        System.out.println("[0]-Return");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                systemUtil.clearDisplay();
                addProduct();
                break;
            case 2:
                systemUtil.clearDisplay();
                viewShoppingCart();
                break;
            case 3:
                systemUtil.clearDisplay();
                finishOrder();
                break;
            case 0:
                uiCostumer();
                break;
            default:
                break;
        }
    }

    private void addProduct(){
        
        shoppingCart.Add(null);
    }

    private void viewShoppingCart(){
        shoppingCart.viewShoppingCart();
    }

    private void finishOrder(){

    }
}
