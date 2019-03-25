package com.stn.tickets.models;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Movie extends Event {

    private static List<Constants.TICKET_TYPES> ticketTypes = Collections.singletonList(Constants.TICKET_TYPES.STANDARD);

    public Movie(Integer id, String name, Date startDate, Date endDate, Location location) {
        super(id, name, startDate, endDate, location);
    }

    @Override
    public List<Constants.TICKET_TYPES> getTicketsTypes() {
        return ticketTypes;
    }
}
