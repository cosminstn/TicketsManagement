package com.stn.tickets.interfaces;

import com.stn.tickets.utils.Constants;
import com.stn.tickets.db.dao.models.Consumer;
import com.stn.tickets.db.dao.models.Event;
import com.stn.tickets.db.dao.models.Ticket;

import java.util.List;

public interface IEventService {

    public Ticket buyTicket(Consumer consumer, Constants.TICKET_TYPES type, String promoCode);

    public List<Ticket> getAvailableTicketsByType(Event ev, Constants.TICKET_TYPES type);

    public List<Ticket> getAvailableTickets(Event ev);

}
