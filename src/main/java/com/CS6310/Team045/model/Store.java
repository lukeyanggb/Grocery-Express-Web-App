package com.CS6310.Team045.model;
import com.CS6310.Team045.exception.BaseException;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "revenue")
    private int revenue;

    @OneToMany
    private List<Item> items;
    @OneToMany
    private List<Drone> drones;
    @OneToMany
    @JoinColumn(name = "orders")
    private List<Order> orders;

    //public Store(){}

    public Store(String name, int revenue){
        this.name = name;
        this.revenue = revenue;
    }
    public void addRevenue(int cost){
        this.revenue += cost;
    }




}
