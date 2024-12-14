import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

@SuppressWarnings("unchecked")
public class Order implements Serializable{
    private int id;
    private String date;
    private float orderTotal;
    private int costumerId;
    private static int numberOfOrder = 0;
    private ArrayList<Product> shoppingCart;
    private static TreeMap<Float, Order> orderMap = new TreeMap<Float, Order>();

    public Order(){
        
    }

    public Order(int costumerId, ArrayList<Product> shoppingCart, float orderTotal){
        id = numberOfOrder++;
        this.date = SystemUtil.setDate();
        this.orderTotal = orderTotal;
        this.costumerId = costumerId;
        this.shoppingCart = shoppingCart;
    }

    public static void finishOrder(float total, Order finishedOrder){
        orderMap.put(total, finishedOrder);
    }

    public static void mostExpensiveOrder(){
        float key = orderMap.lastKey();
        Order mostExpensive =  orderMap.get(key);
        System.out.println("["+mostExpensive.id+"] - Buyer: "+mostExpensive.costumerId+" - Total: $"+mostExpensive.orderTotal+" - Date:"+mostExpensive.date);
    }

    public static void deserialize() throws IOException, ClassNotFoundException{
        File file = new File("../order.dat");
        if(!file.exists() || file.length() == 0){
            return;
        }
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        orderMap = (TreeMap<Float, Order>) ois.readObject();
        numberOfOrder = orderMap.size();
        fis.close();
    }

    public static void serialize() throws IOException{
        FileOutputStream fos = new FileOutputStream("../order.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(orderMap);
        fos.close();
    }
}
