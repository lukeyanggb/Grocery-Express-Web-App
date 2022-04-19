package com.CS6310.Team045.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "items")
public class Item {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "weight")
    private int weight;
    @ManyToOne
    @JoinColumn(name = "store_name")
    private Store store;
    //@OneToMany
    //private List<ItemLine> lines;


   /*
    public Item(String name, int weight, Store store){
        this.name = name;
        this.weight = weight;
        this.store = store;
    }

    public Item(){}

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
    */


}
