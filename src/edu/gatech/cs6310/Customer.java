package edu.gatech.cs6310;

public class Customer {
    private String account;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int rating;
    private int credits;

    public Customer(String account, String firstName, String lastName, String phoneNumber, int rating,
                 int credits) {
        this.account = account;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.credits = credits;
    }

    public void deductCredits(int credits){
        this.credits -= credits;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getRating() {
        return rating;
    }

    public int getCredits() {
        return credits;
    }
}
