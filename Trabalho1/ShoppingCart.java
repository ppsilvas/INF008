package Trabalho1;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShoppingCart {
    private float orderTotal = 0;
    private ArrayList<Product> inCart;

    public void Add(Product product){
        inCart.add(product);
        orderTotal += product.price;
    }

    public void viewShoppingCart(){
        int length = inCart.size();
        Product[] cartList = new Product[length];
        cartList = inCart.toArray(cartList);
        System.out.println("Cart items:"+cartList);
        System.out.println("Order total: "+orderTotal);
    }
}
