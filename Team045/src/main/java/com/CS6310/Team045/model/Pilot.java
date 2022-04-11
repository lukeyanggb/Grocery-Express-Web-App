package com.CS6310.Team045.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pilots")
public class Pilot extends User{
    @Column(name = "account")
    private String account;
    @Column(name = "tax_id")
    private String taxID;
    @Column(name = "license_id")
    private String licenseID;
    @Column(name = "experience")
    private int experience;
    @Column(name = "control")
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
