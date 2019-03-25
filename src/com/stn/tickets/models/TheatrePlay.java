package com.stn.tickets.models;

import java.util.ArrayList;
import java.util.List;

public class TheatrePlay extends Event {


    @Override
    public List<Constants.TICKET_TYPES> getTicketsTypes() {
        List<String> types = new ArrayList<String>();
        types.add("FRONT-ROW");
        types.add("STANDARD");
        return types;
    }
}
