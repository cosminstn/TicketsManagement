package com.stn.tickets.db.dao.models;

import com.stn.tickets.db.dao.models.general.Entity;

import java.sql.Date;

public class Concert extends Event {

    private boolean isExplicit;
    private Artist mainArtist;
    private Artist openingArtist;

    public Concert() {}

    public Concert(boolean isExplicit, Artist mainArtist, Artist openingArtist) {
        this.isExplicit = isExplicit;
        this.mainArtist = mainArtist;
        this.openingArtist = openingArtist;
    }

    public Concert(Integer id, String name, String description, Date startDate, Date endDate, Location location,
                   boolean isExplicit, Artist mainArtist, Artist openingArtist) {
        super(id, name, description, startDate, endDate, location);
        this.isExplicit = isExplicit;
        this.mainArtist = mainArtist;
        this.openingArtist = openingArtist;
    }

    public Concert(Event ev) {
        super(ev.getId(), ev.getName(), ev.getDescription(), ev.getStartDate(), ev.getEndDate(), ev.getLocation());
    }

    public Concert(Event ev, boolean isExplicit, Artist mainArtist, Artist openingArtist) {
        super(ev.getId(), ev.getName(), ev.getDescription(), ev.getStartDate(), ev.getEndDate(), ev.getLocation());
        this.isExplicit = isExplicit;
        this.mainArtist = mainArtist;
        this.openingArtist = openingArtist;
    }

    public boolean isExplicit() {
        return isExplicit;
    }

    public void setExplicit(boolean explicit) {
        isExplicit = explicit;
    }

    public Artist getMainArtist() {
        return mainArtist;
    }

    public void setMainArtist(Artist mainArtist) {
        this.mainArtist = mainArtist;
    }

    public Artist getOpeningArtist() {
        return openingArtist;
    }

    public void setOpeningArtist(Artist openingArtist) {
        this.openingArtist = openingArtist;
    }
}

