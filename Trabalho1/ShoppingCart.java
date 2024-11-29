package Trabalho1;

import java.util.ArrayList;
import java.util.Collection;

public class ShoppingCart {
    private float orderTotal = 0;
    private int costumerId;
    private ArrayList<Product> inCart = new ArrayList<Product>();
    private Product product;

    public ShoppingCart(){
    }

    public ShoppingCart(int costumerId){
        this.costumerId = costumerId;
    }

    public void addInCart(int indexProduct){
        System.out.println(product.getProduct(indexProduct));
        inCart.add(product.getProduct(indexProduct));
        orderTotal += product.getProduct(indexProduct).price;
    }

    public void viewShoppingCart(){
        Product[] cartList = new Product[inCart.size()];
        inCart.toArray(cartList);
        System.out.println("Cart items:"+cartList);
        System.out.println("Order total: "+orderTotal);
    }

    public void finishOrder(int idCostumer){
        Order order = new Order(inCart, orderTotal, idCostumer);
        orderTotal = 0;
        inCart.removeAll(inCart);
    }
}
