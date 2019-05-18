package com.stn.tickets.db.dao.models;

import com.stn.tickets.db.dao.models.general.Entity;

import java.util.Objects;

public class Event extends Entity {

    private String name;
    private String description;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private Location location;

    public Event() {}

    public Event(Integer id, String name, String description, java.sql.Date startDate, java.sql.Date endDate, Location location) {
        super(id);
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;

        if (!Event.class.isAssignableFrom(o.getClass()))
            return false;

        final Event event = (Event) o;
        return Objects.equals(event.getId(), this.getId());
    }
}
