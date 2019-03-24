package com.stn.tickets.models;

import java.util.ArrayList;
import java.util.List;

public class TheatrePlay extends Event {


    @Override
    public List<String> getTicketsTypes() {
        List<String> types = new ArrayList<String>();
        types.add("vip");
        return types;
    }
}
