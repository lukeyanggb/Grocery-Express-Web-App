package com.CS6310.Team045.model;

import javax.persistence.*;
@Entity
@Table(name = "itemLine")
public class ItemLine {
    @Id
    private String item;
    private int unitPrice;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public ItemLine(String item, int unitPrice, int quantity, Order order) {
        this.item = item;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.order = order;
    }
}
