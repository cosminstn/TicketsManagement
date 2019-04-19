package com.stn.tickets.models;

import com.stn.tickets.persistence.PersistentEntity;

public class Location extends PersistentEntity {

    private Integer id;
    private String name;
    private String country;
    private String city;

    private static final String PERSISTENCE_FILE_NAME = "locations.csv";

    public Location() {
        super("locations.csv");
    }

    public Location(Integer id, String name, String country, String city) {
        super(PERSISTENCE_FILE_NAME);
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toCsvLine() throws Exception {
        if (id == null)
            throw new Exception("The id of a persistent entity cannot be null");

        String line = "";
        line += id + ",";
        line += (name) + ",";
        line += (country != null ? country : "") + ",";
        line += (city != null ? city : "");

        return line;
    }
}