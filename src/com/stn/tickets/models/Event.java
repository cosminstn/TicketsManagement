package com.stn.tickets.models;

import java.util.Date;
import java.util.List;

public abstract class Event {

    private Integer id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Location location;

    public Event() {
    }

    public Event(Integer id, String name, Date startDate, Date endDate, Location location) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public abstract List<Constants.TICKET_TYPES> getTicketsTypes();
}
