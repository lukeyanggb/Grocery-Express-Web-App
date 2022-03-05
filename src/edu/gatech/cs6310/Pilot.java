package edu.gatech.cs6310;

public class Pilot extends User {
    private String account;
    private String taxID;
    private String licenseID;
    private int experience;
    private Drone control;

    public Pilot(String account, String firstName, String lastName, String phoneNumber, String taxID,
                 String licenseID, int experience) {
        super(firstName, lastName, phoneNumber);
        this.account = account;
        this.taxID = taxID;
        this.licenseID = licenseID;
        this.experience = experience;
    }

    public void assign(Drone drone){
        if (this.control != null) {
            this.control.removePilot();
        }
        this.control = drone;
    }

    public void remove(){
        this.control = null;
    }

    public void addExperience(){
        this.experience += 1;
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
