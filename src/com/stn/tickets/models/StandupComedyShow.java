package com.stn.tickets.models;

import java.util.Collections;
import java.util.List;

public class StandupComedyShow extends Event {

    private static List<Constants.TICKET_TYPES> ticketTypes = Collections.singletonList(Constants.TICKET_TYPES.STANDARD);

    @Override
    public List<Constants.TICKET_TYPES> getTicketsTypes() {
        return ticketTypes;
    }
}