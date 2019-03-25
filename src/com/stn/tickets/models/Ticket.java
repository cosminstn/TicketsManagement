package com.stn.tickets.models;

public class Ticket {

    private Integer id;
    private Event event;
    private double price;
    private Constants.TICKET_TYPES type;
    private Consumer owner = null;
    private boolean wasUser = false;

    public Ticket() {
    }

    public Ticket(Integer id, Event event, double price, Constants.TICKET_TYPES type, Consumer owner) {
        this.id = id;
        this.event = event;
        this.price = price;
        this.type = type;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Constants.TICKET_TYPES getType() {
        return type;
    }

    public void setType(Constants.TICKET_TYPES type) {
        this.type = type;
    }

    public Consumer getOwner() {
        return owner;
    }

    public void setOwner(Consumer owner) {
        this.owner = owner;
    }
}
