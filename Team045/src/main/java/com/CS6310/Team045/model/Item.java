package com.CS6310.Team045.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "items")
public class Item {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "weight")
    private int weight;
    @Column(name = "store_name")
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
