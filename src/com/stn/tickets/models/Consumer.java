package com.stn.tickets.models;

import com.stn.tickets.persistence.PersistentEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.stn.tickets.utils.Constants.CSV_DATE_TIME_FORMAT;

public class Consumer extends PersistentEntity {

    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String city;
    private String country;
    private String email;

    private static final String PERSISTENCE_FILE_NAME = "consumers.csv";

    public Consumer() {
        super(PERSISTENCE_FILE_NAME);
    }

    public Consumer(Integer id, String firstName, String lastName, Date birthDate, String city, String country, String email) {
        super(PERSISTENCE_FILE_NAME);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.city = city;
        this.country = country;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
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

    @Override
    public String toCsvLine() throws Exception {
        if (id == null)
            throw new Exception("ID cannot be null for persistent entities!");

        SimpleDateFormat dateFormat = new SimpleDateFormat(CSV_DATE_TIME_FORMAT);

        return String.format("%d,%s,%s,%s,%s,%s,%s", id, filterString(firstName), filterString(lastName),
                dateFormat.format(birthDate), filterString(city), filterString(country), filterString(email));
    }
}
