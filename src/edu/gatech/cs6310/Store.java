package edu.gatech.cs6310;
import java.util.Set;
import java.util.TreeMap;

public class Store {
    private String name;
    private int revenue;
    private TreeMap<String, Item> items = new TreeMap<String, Item>();
    private TreeMap<String, Drone> drones = new TreeMap<String, Drone>();

    public Store(String name, int revenue){
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

    public void addDrone(String id, Drone drone){
        if (drones.containsKey(id)){
            System.out.println("ERROR:drone_identifier_already_exists");
        } else {
            drones.put(id, drone);
            System.out.println("OK:change_completed");
        }
    }

    public void displayDrones(){
        Set<String> keys = drones.keySet();
        for (String key: keys) {
            Drone drone = drones.get(key);
            System.out.println("droneID:"+drone.getId()+",total_cap"+drone.getCapacity()+
                    ",num_orders:"+drone.getNumOrders()+",remaining_cap:"+drone.remainingCap()+
                    ",trips_left:"+drone.getTripsBeforeRefueling());
        }
        System.out.println("OK:display_completed");
    }

    public void flyDrone(String id, Pilot pilot){
        if (drones.containsKey(id)){
            Drone drone = drones.get(id);
            drone.assign(pilot.getAccount());
            pilot.assign(id);
            System.out.println("OK:change_completed");
        } else {
            System.out.println("ERROR:drone_identifier_does_not_exist");
        }
    }

    public String getName() {
        return name;
    }
    public double getRevenue() {
        return revenue;
    }
}
