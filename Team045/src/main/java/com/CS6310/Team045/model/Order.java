package com.CS6310.Team045.model;
import java.util.*;
import com.CS6310.Team045.exception.BaseException;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "designated_drone")
    @ManyToOne
    private Drone designatedDrone;
    @ManyToOne
    @JoinColumn (name = "customer_id")
    private Customer requestedBy;
    @ManyToOne
    @JoinColumn( name = "store_name")
    private Store store;

    @OneToMany(mappedBy = "order")
    private List<ItemLine> items;

    // item, {quantity, unitPrice, totalCost, totalWeight}
    //private HashMap<Item, int[]> items = new HashMap<>();
    private int weight = 0;
    private int cost = 0;
    public Order(){}
    public Order(String id, Drone designatedDrone, Customer requestedBy) {
        this.id = id;
        this.designatedDrone = designatedDrone;
        this.requestedBy = requestedBy;
    }



}
