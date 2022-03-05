package edu.gatech.cs6310;

import java.util.ArrayList;

public class Drone {
    private String id;
    private int capacity;
    private int tripsBeforeRefueling;
    private int currentLoad=0;
    private Pilot controlledBy=null;
    private ArrayList<Order> orders = new ArrayList<>();

    public Drone(String id, int capacity, int tripsBeforeRefueling) {
        this.id = id;
        this.capacity = capacity;
        this.tripsBeforeRefueling = tripsBeforeRefueling;
    }

    public void assign(Pilot pilot){
        if (this.controlledBy != null){
            this.controlledBy.remove();
        }
        this.controlledBy = pilot;
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    public void removeOrder(Order order){
        this.currentLoad -= order.getWeight();
        this.orders.remove(order);
    }
    public void removePilot(){
        this.controlledBy = null;
    }

    public boolean checkCap(int weight){
        // check drone has enough remaining capacity to carry the new item as part of its payload.
        try {
            if (weight > (this.capacity-this.currentLoad)) {
                throw new BaseException("ERROR:drone_cant_carry_new_item");
        } else {
            return true;
        }
        } catch (BaseException e) {
            e.printMessage();
            return false;
        }
    }

    public void updateLoad(int weight) {
        this.currentLoad += weight;
    }
    public int remainingCap(){
        return this.capacity-this.currentLoad;
    }

    public void reduceFuel(){
        this.tripsBeforeRefueling -= 1;
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTripsBeforeRefueling() {
        return tripsBeforeRefueling;
    }

    public int getNumOrders() {
        return orders.size();
    }

    public Pilot getControlledBy() {
        return controlledBy;
    }
}
