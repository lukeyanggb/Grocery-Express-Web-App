package com.CS6310.Team045.model;
import java.util.ArrayList;
import java.util.List;

import com.CS6310.Team045.exception.BaseException;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "drones")
public class Drone {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "tripsbefore_refueling")
    private int tripsBeforeRefueling;
    @Column(name = "current_load")
    private int currentLoad=0;
    @ManyToOne
    private Store store;
    @OneToOne
    @JoinColumn(name = "Pilot")
    private Pilot controlledBy;
    @OneToMany
    private List<Order> orders;


    //public Drone(){}

    public Drone(String id, int capacity, int tripsBeforeRefueling) {
        this.id = id;
        this.capacity = capacity;
        this.tripsBeforeRefueling = tripsBeforeRefueling;
    }

    public void addCurrentLoad(int load){
        currentLoad += load;
    }
    public void deductCurrentLoad(int load){
        currentLoad -= load;
    }
    public void deductFuel(){
        this.tripsBeforeRefueling--;
    }


}
