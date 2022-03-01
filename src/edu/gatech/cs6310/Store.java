package edu.gatech.cs6310;
import java.util.Set;
import java.util.TreeMap;

public class Store {
    private String name;
    private int revenue;
    private TreeMap<String, Item> items = new TreeMap<>();
    private TreeMap<String, Drone> drones = new TreeMap<>();
    private TreeMap<String, Order> orders = new TreeMap<>();

    public Store(String name, int revenue){
        this.name = name;
        this.revenue = revenue;
    }

    public void addItem(String name, Item item){
        if (items.containsKey(name)){
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
            if (drone.getControlledBy() != null) {
                Pilot pilot = drone.getControlledBy();
                System.out.println("droneID:"+drone.getId()+",total_cap:"+drone.getCapacity()+
                        ",num_orders:"+drone.getNumOrders()+",remaining_cap:"+drone.remainingCap()+
                        ",trips_left:"+drone.getTripsBeforeRefueling()+",flown_by:"+
                        pilot.getFirstName()+"_"+pilot.getLastName());
            } else {
                System.out.println("droneID:"+drone.getId()+",total_cap:"+drone.getCapacity()+
                        ",num_orders:"+drone.getNumOrders()+",remaining_cap:"+drone.remainingCap()+
                        ",trips_left:"+drone.getTripsBeforeRefueling());
            }
        }
        System.out.println("OK:display_completed");
    }

    public void flyDrone(String id, Pilot pilot){
        if (drones.containsKey(id)){
            Drone drone = drones.get(id);
            drone.assign(pilot);
            pilot.assign(drone);
            System.out.println("OK:change_completed");
        } else {
            System.out.println("ERROR:drone_identifier_does_not_exist");
        }
    }

    public void createOrder(String id, String droneID, Customer customer){
        Drone drone = drones.get(droneID);
        Order order = new Order(id, drone, customer);
        orders.put(id, order);
        drone.addOrder(order);
        System.out.println("OK:change_completed");
    }

    public void purchaseOrder(String id){
        if (orders.containsKey(id)) {
            Order order = orders.get(id);
            Drone drone = order.getDesignatedDrone();
            Pilot pilot = drone.getControlledBy();
            Customer customer = order.getRequestedBy();
            if (pilot != null && drone.getTripsBeforeRefueling()>=1){
                // the cost of the order must be deducted from the customer’s credits;
                int cost = order.getCost();
                customer.deductCredits(cost);
                // the cost of the order must be added to the store’s revenue;
                this.revenue += cost;
                // the number of remaining deliveries (i.e., fuel) for the drone must be reduced by one;
                drone.reduceFuel();
                drone.removeOrder(order);
                // the pilot’s experience (i.e., number of successful deliveries) must be increased by one;
                pilot.addExperience();
                // the order must otherwise be removed from the system.
                orders.remove(id);
                System.out.println("OK:change_completed");
            } else if (pilot == null){
                System.out.println("ERROR:drone_needs_pilot");
            } else {
                System.out.println("ERROR:drone_needs_fuel");
            }
        } else {
            System.out.println("ERROR:order_identifier_does_not_exist");
        }
    }

    public void cancelOrder(String id){
        if (orders.containsKey(id)) {
            Order order = orders.get(id);
            Customer customer = order.getRequestedBy();
            Drone drone = order.getDesignatedDrone();
            // release pending cost to credits
            customer.addOutstandingOrders(-order.getCost());
            // remove from drone's orders list
            drone.removeOrder(order);
            // remove from orders list
            orders.remove(id);
            System.out.println("OK:change_completed");
        } else {
            System.out.println("ERROR:order_identifier_does_not_exist");
        }
    }
    public void displayOrders(){
        Set<String> keys = orders.keySet();
        for (String key: keys) {
            Order order = orders.get(key);
            order.displayOrder();
        }
        System.out.println("OK:display_completed");
    }

    public void requestItem(String orderID, String itemName, int quantity, int unitPrice){
        if (!orders.containsKey(orderID)){
            System.out.println("ERROR:order_identifier_does_not_exist");
        } else if (!items.containsKey(itemName)) {
            System.out.println("ERROR:item_identifier_does_not_exist");
        } else {
            Order order = orders.get(orderID);
            Drone drone = order.getDesignatedDrone();
            Customer customer = order.getRequestedBy();
            Item item = items.get(itemName);
            int cost = quantity*unitPrice;
            int weight = item.getWeight()*quantity;
            // item already ordered
            if (order.hasItem(itemName)){
                System.out.println("ERROR:item_already_ordered");
                // check the customer has enough remaining credits to afford the new item;
            } else if (customer.hasCredits(cost) && drone.checkCap(weight)) {
                customer.addOutstandingOrders(cost);
                drone.updateLoad(weight);
                order.requestItem(item, quantity, unitPrice);
                System.out.println("OK:change_completed");
            }
        }
    }
    public boolean hasOrder(String id) {
        return orders.containsKey(id);
    }

    public boolean hasDrone(String id) {
        return drones.containsKey(id);
    }

    public String getName() {
        return name;
    }
    public int getRevenue() {
        return revenue;
    }
}
