package com.CS6310.Team045.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.CS6310.Team045.exception.BaseException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "drones")
public class Drone implements Serializable {
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
    @JsonManagedReference
    @JoinColumn(name = "store_name", insertable=false)
    private Store store = null;
    private String sname;

    @OneToOne
    @JoinColumn(name = "controlledBy_account")
    private Pilot controlledBy;
    @OneToMany
    @JsonManagedReference
    private List<Order> orders;


    //public Drone(){}

    public Drone(String id, Integer capacity, Integer tripsBeforeRefueling) {
        this.id = id;
        this.capacity = capacity;
        this.tripsBeforeRefueling = tripsBeforeRefueling;
    }
    public void assign(Pilot pilot){
        if (this.controlledBy != null){
            this.controlledBy.remove();
        }
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
