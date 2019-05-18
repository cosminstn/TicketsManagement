package com.stn.tickets.db.dao.models;

import com.stn.tickets.db.dao.models.general.Entity;
import com.stn.tickets.enums.TicketTypes;

public class Ticket extends Entity {

    private Event event;
    private double price;
    private TicketTypes type;
    private Consumer owner = null;

    public Ticket() {

    }

    public Ticket(Integer id, Event event, double price, TicketTypes type, Consumer owner) {
        super(id);
        this.event = event;
        this.price = price;
        this.type = type;
        this.owner = owner;
    }

    public Ticket(Event event, double price, TicketTypes type, Consumer owner) {
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

    public TicketTypes getType() {
        return type;
    }

    public void setType(TicketTypes type) {
        this.type = type;
    }

    public Consumer getOwner() {
        return owner;
    }

    public void setOwner(Consumer owner) {
        this.owner = owner;
    }
}