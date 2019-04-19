package com.stn.tickets.models;

import com.stn.tickets.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Movie extends Event {

    private static List<Constants.TICKET_TYPES> ticketTypes = Collections.singletonList(Constants.TICKET_TYPES.STANDARD);

    public Movie() {

    }

    public Movie(Integer id, String name, String description, Date startDate, Date endDate, Location location) {
        super(id, name, description, startDate, endDate, location);
    }

    @Override
    public List<Constants.TICKET_TYPES> getTicketsTypes() {
        return ticketTypes;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return String.format("Movie: %s on %s at %s, loc: %s", getName(), dateFormat.format(getStartDate()), timeFormat.format(getStartDate()), getLocation().toString());
    }
}
