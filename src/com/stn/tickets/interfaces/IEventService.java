package com.stn.tickets.interfaces;

import com.stn.tickets.models.Constants;
import com.stn.tickets.models.Consumer;
import com.stn.tickets.models.Ticket;

import java.util.List;

public interface IEventService {

    public Ticket buyTicket(Consumer consumer, Constants.TICKET_TYPES type, String promoCode);

    public List<Ticket> getAvailableTicketsByType(Constants.TICKET_TYPES type);

    public List<Ticket> getAvailableTickets();

}
