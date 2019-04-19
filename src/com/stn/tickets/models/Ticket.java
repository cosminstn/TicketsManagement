package com.stn.tickets.models;

import com.stn.tickets.persistence.PersistentEntity;
import com.stn.tickets.utils.Constants;

public class Ticket extends PersistentEntity {

    private Integer id;
    private Event event;
    private double price;
    private Constants.TICKET_TYPES type;
    private Consumer owner = null;
    private boolean wasUsed = false;

    private static final String PERSISTENCE_FILE_NAME = "tickets.csv";

    public Ticket() {
        super(PERSISTENCE_FILE_NAME);
    }

    public Ticket(Integer id, Event event, double price, Constants.TICKET_TYPES type, Consumer owner) {
        super(PERSISTENCE_FILE_NAME);
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

    @Override
    public String toCsvLine() throws Exception {
//        id
//        eventId
//        price
//        TICKET_TYPE_ID
//        consumerId
//        was used
        if (id == null)
            throw new Exception("Id cannot be null for persistent entities!");
        return String.format("%d,%d,%.2f,%d,%s,%b", id, event.getId(), price, type.getId(),
                owner != null ? (owner.getId() != null ? owner.getId() : "") : "", wasUsed);
    }
}
