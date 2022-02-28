package edu.gatech.cs6310;

public class Item {
    private String name;
    private Double weight;
    private String store;

    public Item(String name, Double weight, String store){
        this.name = name;
        this.weight = weight;
        this.store = store;
    }

    public String getName() {
        return name;
    }

    public Double getWeight() {
        return weight;
    }
}
