package edu.gatech.cs6310;
import java.util.Set;
import java.util.TreeMap;

public class Store {
    private String name;
    private double revenue;
    private TreeMap<String, Item> items = new TreeMap<String, Item>();

    public Store(String name, double revenue){
        this.name = name;
        this.revenue = revenue;
    }

    public void addItem(String name, Item item){
        if (items.containsKey(item)){
            System.out.println("ERROR:item_identifier_already_exists");
        } else {
            items.put(name, item);
            System.out.println("OK:change_completed");
        }
    }

    public void displayItems(){
        Set<String> keys = items.keySet();
        for (String key: keys) {
            Item item = items.get(key);
            System.out.println(item.getName() + "," + item.getWeight());
        }
    }

    public String getName() {
        return name;
    }
    public double getRevenue() {
        return revenue;
    }
}
