package com.CS6310.Team045.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Entity
@Table(name = "items")
public class Item implements Serializable {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "weight")
    private Integer weight;

    @ManyToOne
    @JoinColumn(name = "store",referencedColumnName = "name", nullable = false)
    private Store store;

    //private String sname;
    //@OneToMany
    //private List<ItemLine> lines;


//    public Item(String name, Integer weight, Store store){
//        this.name = name;
//        this.weight = weight;
//        this.store = store;
//    }

//    public Item(){}


}
