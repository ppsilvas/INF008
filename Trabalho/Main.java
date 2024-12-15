package Trabalho;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import Trabalho.io.dataIO;
import Trabalho.model.Administrator;
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
        // dataIO.serealize(users, products, orders);
        Object[] data = dataIO.deserialize();
        users = (HashMap<String, User>) data[0];
        products = (ArrayList<Product>) data[1];
        orders = (TreeMap<Float, Order>) data[2];
        UI ui = new UI();
        ui.run(users, products, orders);
        dataIO.serealize(users, products, orders);
        System.exit(0);
    }
}
