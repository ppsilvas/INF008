import java.util.ArrayList;
import java.util.Date;

public class ShoppingCart {
    protected static ArrayList<Product> shoppingCart = new ArrayList<Product>();
    private static float total =0;

    public static void addCart(Product product){
        if(!product.verifyProductInventory()){
            System.out.println("Out of stock");
            return;
        }
        shoppingCart.add(product);
        total += product.price;
    }

    public static void showCart(){
        for(Product product:shoppingCart)
            product.display();
    }

    public static void finishOrder(int costumerId){
        Order order = new Order(costumerId, shoppingCart, total);
        for(Product currentProduct: shoppingCart){
            Product.productBought(currentProduct.id);
        }
        Order.finishOrder(costumerId, order);
    }

}
