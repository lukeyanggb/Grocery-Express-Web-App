package com.CS6310.Team045.model;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString
@Entity
@Table(name = "itemLine")
public class ItemLine{
    @Id
    @Column(name = "item")
    //@ManyToOne
    private String item;
    private int unitPrice;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    public ItemLine(String item,int unitPrice,int quantity,Order order){
        this.item = item;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.order =order;
    }


    /*public String itemLineToString(){
        int weight = quantity*item.getWeight();
        String res = "item_name:" + item.getName()+",total_quantity:"+quantity+",total_cost:"+ quantity*unitPrice
                +",total_weight:"+weight;
        return res;
    }*/

}
