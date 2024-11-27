package Trabalho1;

import java.util.Scanner;

public class UICostumer {
    private Scanner scanner = new Scanner(System.in);
    private SystemUtil systemUtil = new SystemUtil();
    private ShoppingCart shoppingCart;
    private Product product;

    public void uiCostumer(int id){
        shoppingCart = new ShoppingCart(id);
        systemUtil.clearDisplay();
        System.out.println("[1]-Start New Order");
        System.out.println("[0]-Exit");
        int choice = scanner.nextInt();
        if(choice == 1){
            startNewOrder(id);
        }else{
            handleLogout();
        }
    }

    private void handleLogout(){
        shoppingCart = null;
    }

    private void startNewOrder(int id){
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
                uiCostumer(id);
                break;
            default:
                break;
        }
    }

    private void addProduct(){
        int response = 1;
        while(response == 1){
            systemUtil.clearDisplay();
            product.productList();
            int choice = scanner.nextInt();
            shoppingCart.addInCart(choice);
            System.out.println("Continue adding?\n[1]-YES\n[0]-NO");
            response = scanner.nextInt();
        }
    }

    private void viewShoppingCart(){
        shoppingCart.viewShoppingCart();
    }

    private void finishOrder(){
        shoppingCart.finishOrder();
    }
}
