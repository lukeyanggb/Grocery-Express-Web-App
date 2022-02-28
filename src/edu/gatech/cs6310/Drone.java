package edu.gatech.cs6310;

import java.util.ArrayList;

public class Drone {
    private String store;
    private String id;
    private int capacity;
    private int tripsBeforeRefueling;
    private String controlledBy;
    private ArrayList<String> orders;

    public Drone(String store, String id, int capacity, int tripsBeforeRefueling) {
        this.store = store;
        this.id = id;
        this.capacity = capacity;
        this.tripsBeforeRefueling = tripsBeforeRefueling;
    }

    public void assign(String pilotAccount){
        this.controlledBy = pilotAccount;
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

    public int remainingCap(){
        int load = 0;
//        for (int i=0; i < orders.size(); i++){
//
//        }
        return this.capacity-load;
    }
}
