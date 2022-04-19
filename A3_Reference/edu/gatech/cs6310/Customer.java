package edu.gatech.cs6310;

public class Customer extends User {
    private String account;
    private int rating;
    private int credits;
    private int outstandingOrders=0;

    public Customer(String account, String firstName, String lastName, String phoneNumber, int rating,
                 int credits) {
        super(firstName, lastName, phoneNumber);
        this.account = account;
        this.rating = rating;
        this.credits = credits;
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
