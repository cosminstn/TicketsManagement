package com.stn.tickets.models;

import java.util.ArrayList;
import java.util.List;

public class StandupComedyShow extends Event {

    @Override
    public List<Constants.TICKET_TYPES> getTicketsTypes() {
        List<String> types = new ArrayList<>();
        types.add("STANDARD");
        return types;
    }
}