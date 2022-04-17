package com.CS6310.Team045.model;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "control")
    private Drone control;

    public Pilot(){}

    public Pilot(String account, String firstName, String lastName, String phoneNumber, String taxID,
                 String licenseID, int experience) {
        super(firstName, lastName, phoneNumber);
        this.account = account;
        this.taxID = taxID;
        this.licenseID = licenseID;
        this.experience = experience;
        control = new Drone();
    }

}
