package edu.gatech.cs6310;
import java.util.*;


public class Order {
    private String id;
    private Drone designatedDrone;
    private Customer requestedBy;
    private TreeMap<String, Item> itemNames = new TreeMap<>();
    // item, {quantity, unitPrice, totalCost, totalWeight}
    private HashMap<Item, int[]> items = new HashMap<>();
    private int weight = 0;
    private int cost = 0;

    public Order(String id, Drone designatedDrone, Customer requestedBy) {
        this.id = id;
        this.designatedDrone = designatedDrone;
        this.requestedBy = requestedBy;
    }
    public void requestItem(Item item, int quantity, int unitPrice){
        if (items.containsKey(item)){
            System.out.println("ERROR:item_already_ordered");
        } else {
            int cost = quantity*unitPrice;
            int weight = quantity*item.getWeight();
            int[] itemInfo = {quantity, unitPrice, cost, weight};
            items.put(item, itemInfo);
            itemNames.put(item.getName(), item);
            this.weight += weight;
            this.cost += cost;
        }
    }

    public boolean hasItem(String itemName){
        return itemNames.containsKey(itemName);
    }

    public void displayOrder(){
        System.out.println("orderID:"+this.id);
        Set<String> keys = itemNames.keySet();
        for (String key: keys) {
            Item item = itemNames.get(key);
            int[] itemInfo = items.get(item);
            //item_name:pot_roast,total_quantity:3,total_cost:30,total_weight:15
            //itemInfo:{quantity, unitPrice, totalCost, totalWeight}
            System.out.println("item_name:"+item.getName()+",total_quantity:"+itemInfo[0]+
                    ",total_cost:"+itemInfo[2]+",total_weight:"+itemInfo[3]);
        }
    }

    public String getId() {
        return id;
    }

    public Drone getDesignatedDrone() {
        return designatedDrone;
    }

    public Customer getRequestedBy() {
        return requestedBy;
    }

    public int getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }
}
