package com.stn.tickets.db.dao.models;

import com.stn.tickets.db.dao.models.general.Entity;

public class Location extends Entity {

    private String name;
    private String country;
    private String city;

    public Location(Integer id, String name, String country, String city) {
        super(id);
        this.name = name;
        this.country = country;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("Country: %s City: %s Name: %s", getCountry(), getCity(), getName());
    }
}