package edu.gatech.cs6310;

public class Item {
    private String name;
    private int weight;
    private String store;

    public Item(String name, int weight, String store){
        this.name = name;
        this.weight = weight;
        this.store = store;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}
