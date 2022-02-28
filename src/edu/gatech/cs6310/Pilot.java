package edu.gatech.cs6310;

public class Pilot {
    private String account;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String taxID;
    private String licenseID;
    private Integer experience;

    public Pilot(String account, String firstName, String lastName, String phoneNumber, String taxID,
                 String licenseID, Integer experience) {
        this.account = account;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.taxID = taxID;
        this.licenseID = licenseID;
        this.experience = experience;
    }
}
