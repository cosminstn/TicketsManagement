package com.stn.tickets.services;

import com.stn.tickets.models.Constants;
import com.stn.tickets.models.Event;
import com.stn.tickets.models.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TicketsService {
    private static int NEXT_TICKET_ID = 1;
    private static List<Ticket> tickets;

    private static TicketsService instance = new TicketsService();

    private TicketsService() {
        tickets = new ArrayList<>();
    }

    public static TicketsService getInstance() {
        return instance;
    }

    public void createTickets(Event event, Constants.TICKET_TYPES type, double price, int count) throws Exception {
        if (price <= 0)
            throw new Exception("Invalid price!");
        if (count <= 0)
            throw new Exception("Count must be > 0 !");
        if (event == null)
            throw new Exception("Null event");
        if (type == null)
            throw new Exception("Null ticket type");
        if (event.getTicketsTypes().indexOf(type) < 0)
            throw new Exception("This ticket type is not supported by the given event");
        for (int i = 0; i < count; i++) {
            Ticket t = new Ticket();
            t.setId(getNextTicketId());
            t.setPrice(price);
            t.setOwner(null);   // it's not bought yet
            t.setEvent(event);
            t.setType(type);
            tickets.add(t);
        }
    }

    public List<Ticket> getTicketsByEvent(Event event) {
        List<Ticket> eventTickets = new ArrayList<>();
        for (Ticket ticket : tickets)
            if (ticket.getEvent() == event)
                eventTickets.add(ticket);
        return eventTickets;
    }

    public HashMap<Constants.TICKET_TYPES, List<Ticket>> getAvailableTicketsMap(Event event) throws Exception {
        if (event == null)
            throw new Exception("Null event!");

        HashMap<Constants.TICKET_TYPES, List<Ticket>> map = new HashMap<>();

        List<Constants.TICKET_TYPES> types = event.getTicketsTypes();
        if (types == null)
            throw new Exception("Invalid event ticket types");
        //Initialize the map
        for (Constants.TICKET_TYPES type : event.getTicketsTypes()) {
            map.put(type, new ArrayList<>());
            for (Ticket ticket : tickets)
                if (ticket.getType() == type && ticket.getEvent() == event)
                    map.get(type).add(ticket);
        }
        return map;
    }

    public List<Ticket> getAvailableTickets(Event event) throws Exception {
        if (event == null)
            throw new Exception("Null event!");

        List<Ticket> eventTickets = new ArrayList<>();
        for (Ticket ticket : tickets)
            if (ticket.getEvent() == event && ticket.getOwner() == null)
                eventTickets.add(ticket);
        return eventTickets;
    }

    private int getNextTicketId() {
        return NEXT_TICKET_ID++;
    }

}
