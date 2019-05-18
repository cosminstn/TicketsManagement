package com.stn.tickets.db.dao.models;

public class Movie extends Event {

    private int minimumAge;
    private Artist biggestStar;
    private int durationInMinutes;

    public Movie() {}

    public Movie(Event ev) {
        super(ev.getId(), ev.getName(), ev.getDescription(), ev.getStartDate(), ev.getEndDate(), ev.getLocation());
    }

    public Movie(Event ev, int minimumAge, Artist biggestStar, int durationInMinutes) {
        super(ev.getId(), ev.getName(), ev.getDescription(), ev.getStartDate(), ev.getEndDate(), ev.getLocation());
        this.minimumAge = minimumAge;
        this.biggestStar = biggestStar;
        this.durationInMinutes = durationInMinutes;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public Artist getBiggestStar() {
        return biggestStar;
    }

    public void setBiggestStar(Artist biggestStar) {
        this.biggestStar = biggestStar;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}
