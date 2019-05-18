package com.stn.tickets.db.dao.models;

import com.stn.tickets.db.dao.models.general.Entity;

public class Consumer extends Entity {

    private String firstName;
    private String lastName;
    private java.sql.Date birthDate;
    private String city;
    private String country;
    private String email;
    
    public Consumer() {}

    public Consumer(Integer id, String firstName, String lastName, java.sql.Date birthDate, String city, String country, String email) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.city = city;
        this.country = country;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public java.sql.Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(java.sql.Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}