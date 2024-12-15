package Trabalho;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import Trabalho.io.dataIO;
import Trabalho.model.Administrator;
import Trabalho.model.Costumer;
import Trabalho.model.Order;
import Trabalho.model.Product;
import Trabalho.model.User;
import Trabalho.ui.UI;

public class Main {
    public static HashMap<String, User> users = new HashMap<String, User>();
    public static ArrayList<Product> products = new ArrayList<Product>();
    public static TreeMap<Float,Order> orders = new TreeMap<Float, Order>();
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // Administrator a = new Administrator("admin", "admin", "admin");
        // users.put("admin", a);
        // Costumer c = new Costumer("Pedro", "pedro", "pedro", "Rua 1");
        // users.put("pedro",c);
        // Product p1 = new Product("Calca", "Calca Jeans", 159.99f, 3, "Vestuario");
        // products.add(p1);
        // Product p2 = new Product("Banana", "Penca de banana", 2.5f, 15, "Frutas");
        // products.add(p2);
        // Product p3 = new Product("Iphone 15", "Smartphone Apple de 10 polegada", 10000, 1, "Eletronicos");
        // products.add(p3);
        // dataIO.serealize(users, products, orders);
        // dataIO.staticSerialize(User.getNumberOfUsers(), Product.getNumberOfProducts(), Order.getNumberOfOrders());
        Object[] data = dataIO.deserialize();
        Object[] staticData = dataIO.staticDeserialize();
        users = (HashMap<String, User>) data[0];
        products = (ArrayList<Product>) data[1];
        orders = (TreeMap<Float, Order>) data[2];
        User.setNumberOfUsers((Integer)staticData[0]);
        Product.setNumberOfProducts((Integer)staticData[1]);
        Order.setNumberOfOrders((Integer)staticData[2]);
        UI ui = new UI();
        ui.run(users, products, orders);
        dataIO.serealize(users, products, orders);
        dataIO.staticSerialize(User.getNumberOfUsers(), Product.getNumberOfProducts(), Order.getNumberOfOrders());
        System.exit(0);
    }
}
