package com.stn.tickets.services;

import com.stn.tickets.db.dao.models.Consumer;
import com.stn.tickets.db.dao.models.Ticket;
import com.stn.tickets.persistence.PersistenceService;

import java.util.ArrayList;
import java.util.List;

public class ConsumersService {

    private List<Consumer> consumers;
    private PersistenceService<Consumer> persistenceService;
    private TicketsService ticketsService;
    private Integer nextConsumerId = 0;

    private static ConsumersService instance = new ConsumersService();

    private ConsumersService() {
        consumers = new ArrayList<Consumer>();
        ticketsService = TicketsService.getInstance();
        persistenceService = new PersistenceService<>(new Consumer());
        consumers = persistenceService.loadPersistentList();
    }

    public static ConsumersService getInstance() {
        return instance;
    }

    public Integer getNextConsumerId() {
        return ++nextConsumerId;
    }

    public void registerConsumer(Consumer cons) throws Exception {
        if (cons.getFirstName() == null)
            throw new Exception("first name missing");
        if (cons.getLastName() == null)
            throw new Exception("last name missing");
        cons.setId(getNextConsumerId());

        consumers.add(cons);
    }

    private List<Ticket> getConsumerTickets(Consumer consumer) {

        return null;
    }

    public Consumer getConsumerById(int id) {
        for (Consumer c : consumers)
            if (c.getId() == id)
                return c;
        return null;
    }

    public boolean persistData() {
        try {
            persistenceService.persistList(consumers);
            return false;
        } catch (Exception e) {
            System.out.println("Could not persist consumers list");
            e.printStackTrace();
            return false;
        }
    }

}
