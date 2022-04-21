package com.CS6310.Team045.model;
import java.util.ArrayList;
import java.util.List;

import com.CS6310.Team045.exception.BaseException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@ToString
@Table(name = "drone")
public class Drone {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "tripsbefore_refueling")
    private Integer tripsBeforeRefueling;
    @Column(name = "current_load")
    private Integer currentLoad=0;

    @ManyToOne
    @JoinColumn(name = "store")
    private Store store;
    //private String sname;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "control")
    @JoinColumn(name = "account")
    private Pilot controlledBy;

    @JsonIgnore
    @OneToMany(mappedBy = "designatedDrone")
    private List<Order> orders;


    //public Drone(){}

    public Drone(String id, Integer capacity, Integer tripsBeforeRefueling) {
        this.id = id;
        this.capacity = capacity;
        this.tripsBeforeRefueling = tripsBeforeRefueling;
    }

    public Drone(String id, Integer capacity, Integer tripsBeforeRefueling, Store store) {
        this.id = id;
        this.capacity = capacity;
        this.tripsBeforeRefueling = tripsBeforeRefueling;
        this.store = store;
    }

    public void assign(Pilot pilot){

        this.controlledBy = pilot;

    }
    public void removePilot(){
        this.controlledBy = null;
    }

    public void addCurrentLoad(Integer load){
        currentLoad += load;
    }
    public void deductCurrentLoad(Integer load){
        currentLoad -= load;
    }
    public void deductFuel(){
        this.tripsBeforeRefueling--;
    }


}
