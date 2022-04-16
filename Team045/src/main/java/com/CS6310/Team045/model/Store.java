package com.CS6310.Team045.model;
import com.CS6310.Team045.exception.BaseException;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;


@Entity
@Table(name = "stores")
public class Store {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "revenue")
    private int revenue;

    @OneToMany
    private List<Item> items;
    @OneToMany
    private List<Drone> drones;
    @ManyToOne
    private Order order;

    public Store(){}

    public Store(String name, int revenue){
        this.name = name;
        this.revenue = revenue;
    }



}
