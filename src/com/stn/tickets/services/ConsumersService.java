package com.stn.tickets.services;

import com.stn.tickets.models.Consumer;
import com.stn.tickets.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class ConsumersService {

    private List<Consumer> consumers;

    private ConsumersService instance = new ConsumersService();
    private TicketsService ticketsService;

    private ConsumersService() {
        consumers = new ArrayList<Consumer>();
        ticketsService = TicketsService.getInstance();
    }

    public ConsumersService getInstance() {
        return instance;
    }

    private List<Ticket> getConsumerTickets(Consumer consumer) {

        return null;
    }

}
