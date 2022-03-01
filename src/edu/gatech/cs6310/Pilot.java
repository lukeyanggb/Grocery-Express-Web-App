package edu.gatech.cs6310;

public class Pilot {
    private String account;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String taxID;
    private String licenseID;
    private int experience;
    private Drone control;

    public Pilot(String account, String firstName, String lastName, String phoneNumber, String taxID,
                 String licenseID, int experience) {
        this.account = account;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.taxID = taxID;
        this.licenseID = licenseID;
        this.experience = experience;
    }

    public void assign(Drone drone){
        if (this.control != null) {
            this.control.remove();
        }
        this.control = drone;
    }

    public void remove(){
        this.control = null;
    }

    public void addExperience(){
        this.experience += 1;
    }
    public String getAccount() {
        return account;
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

    public String getTaxID() {
        return taxID;
    }

    public String getLicenseID() {
        return licenseID;
    }

    public int getExperience() {
        return experience;
    }
}
