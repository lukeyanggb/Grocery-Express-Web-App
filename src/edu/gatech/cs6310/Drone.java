package edu.gatech.cs6310;

import java.util.ArrayList;

public class Drone {
    private String store;
    private String id;
    private int capacity;
    private int tripsBeforeRefueling;
    private int currentLoad;
    private Pilot controlledBy;
    private ArrayList<Order> orders = new ArrayList<>();

    public Drone(String store, String id, int capacity, int tripsBeforeRefueling, int currentLoad) {
        this.store = store;
        this.id = id;
        this.capacity = capacity;
        this.tripsBeforeRefueling = tripsBeforeRefueling;
        this.currentLoad = currentLoad;
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
        this.orders.remove(order);
    }
    public void remove(){
        this.controlledBy = null;
    }
    public void addWeight(int weight){

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

    public double getCapacity() {
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
