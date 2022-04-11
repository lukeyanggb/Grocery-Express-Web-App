package com.CS6310.Team045.model;

import com.CS6310.Team045.exception.BaseException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends User{
    @Column(name = "account")
    private String account;
    @Column(name = "rating")
    private int rating;
    @Column(name = "credits")
    private int credits;
    @Column(name = "outstanding_orders")
    private int outstandingOrders=0;
    private String password;

    public Customer(String account, String firstName, String lastName, String phoneNumber, int rating,
                    int credits, String password) {
        super(firstName, lastName, phoneNumber);
        this.account = account;
        this.rating = rating;
        this.credits = credits;
        this.password = password;
    }

    // check the customer has enough remaining credits to afford the new item;
    public boolean hasCredits(int cost){
        try {
            if ((this.credits-this.outstandingOrders) >= cost) {
                return true;
            } else {
                throw new BaseException("ERROR:customer_cant_afford_new_item");
            }
        } catch (BaseException e) {
            e.printMessage();
            return false;
        }
    }
    public void addOutstandingOrders(int cost){
        this.outstandingOrders += cost;
    }

    public void deductCredits(int cost){
        this.credits-=cost;
    }

    public int getRating() {
        return rating;
    }

    public int getCredits() {
        return credits;
    }

}
