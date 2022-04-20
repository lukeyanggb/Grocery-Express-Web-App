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
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "revenue")
    private Integer revenue;

    @OneToMany
    private List<Item> items;
    @OneToMany
    private List<Drone> drones;
    @OneToMany
    private List<Drone> pilots;
    @OneToMany
    @JoinColumn(name = "orders_id")
    private List<Order> orders;

    //public Store(){}

    public Store(String name, Integer revenue){
        this.name = name;
        this.revenue = revenue;
    }
    public void addRevenue(Integer cost){
        this.revenue += cost;
    }




}
