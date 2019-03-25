package com.stn.tickets.services;

import com.stn.tickets.interfaces.IEventService;
import com.stn.tickets.models.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventsService implements IEventService {

    private List<Event> events;

    private static EventsService instance = new EventsService();

    private EventsService() {
        events = new ArrayList<>();
    }

    public void createEvent(String name, String description, Date startDate, Date endDate, Location location) {

    }

    public static EventsService getInstance() {
        return instance;
    }

    private HashMap<Class<? extends Event>, List<Ticket>> eventTickets;

    @Override
    public Ticket buyTicket(Consumer consumer, Constants.TICKET_TYPES type, String promoCode) {
        return null;
    }

    @Override
    public List<Ticket> getAvailableTicketsByType(Class<? extends Event> ev, Constants.TICKET_TYPES type) {
        return null;
    }

    @Override
    public List<Ticket> getAvailableTickets(Class<? extends Event> ev) {
        return null;
    }
}
