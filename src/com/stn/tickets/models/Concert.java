package com.stn.tickets.models;

import java.util.ArrayList;
import java.util.List;

public class Concert extends Event {


    @Override
    public List<Constants.TICKET_TYPES> getTicketsTypes() {
        List<Constants.TICKET_TYPES> types = new ArrayList<>();
        types.add(Constants.TICKET_TYPES.VIP);
        types.add(Constants.TICKET_TYPES.BACKSTAGE);
        types.add(Constants.TICKET_TYPES.STANDARD);
        return types;
    }
}
