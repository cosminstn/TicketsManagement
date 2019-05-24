package com.stn.tickets.db.dao.services;

import com.stn.tickets.db.dao.models.Consumer;
import com.stn.tickets.db.dao.models.Event;
import com.stn.tickets.db.dao.models.Ticket;
import com.stn.tickets.db.dao.services.general.EntityDAO;
import com.stn.tickets.db.engine.PreparedStatementParameter;
import com.stn.tickets.enums.TicketTypes;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketDAO extends EntityDAO<Ticket> {

    private static TicketDAO instance;

    private TicketDAO() {
        super("TICKETS", "TICKET_ID", false);
    }

    public static TicketDAO getInstance() {
        if (instance == null)
            instance = new TicketDAO();
        return instance;
    }

    @Override
    public List<String> getColumnsNamesWithoutPK() {
        return Arrays.asList("EVENT_ID", "PRICE", "TYPE_ID", "CONSUMER_ID");
    }

    @Override
    public Ticket castFromObjectArray(Object[] arr) throws Exception {
        int colIndex = 0;

        Ticket ticket = new Ticket();
        ticket.setId((Integer) arr[colIndex++]);

        int evId = (Integer) arr[colIndex++];
        Event ev = EventDAO.getInstance().getEntity(evId);
        ticket.setEvent(ev);

        int typeId = (Integer) arr[colIndex++];
        TicketTypes type = TicketTypes.getTicketTypeById(typeId);
        ticket.setType(type);

        Integer consumerId = (Integer) arr[colIndex];
        if (consumerId != null) {
            Consumer consumer = ConsumerDAO.getInstance().getEntity(consumerId);
            ticket.setOwner(consumer);
        }
        return ticket;
    }

    public int createEventTickets(Event ev, double price, TicketTypes type, int count) throws Exception {
        if (ev == null)
            throw new Exception("Null event");
        if (count <= 0)
            throw new Exception("count has to be > 1");
        if (ev.getId() == null)
            throw new Exception("Event is missing id!");
        if (!EventDAO.getInstance().isEntityPersistent(ev.getId()))
            throw new Exception("Not persistent event!");
        List<Ticket> ticketsBatch = new ArrayList<>();
        for (int i = 0; i < count; i++)
            ticketsBatch.add(new Ticket(ev, price, type, null));

        return createEntityBatch(ticketsBatch);
    }

    @Override
    public List<PreparedStatementParameter> castToParamsList(Ticket entity, boolean includePK) {
        List<PreparedStatementParameter> params = new ArrayList<>();

        int colIndex = 1;
        if (includePK)
            params.add(new PreparedStatementParameter<Integer>(colIndex++, entity.getId(), Types.INTEGER));
        params.add(new PreparedStatementParameter<Integer>(colIndex++, entity.getEvent().getId(), Types.INTEGER));
        params.add(new PreparedStatementParameter<Double>(colIndex++, entity.getPrice(), Types.DECIMAL));
        params.add(new PreparedStatementParameter<Integer>(colIndex++, entity.getType().getId(), Types.INTEGER));
        if (entity.getOwner() != null)
            params.add(new PreparedStatementParameter<Integer>(colIndex, entity.getOwner().getId(), Types.INTEGER));
        else
            params.add(new PreparedStatementParameter<Integer>(colIndex, null, Types.INTEGER));
        return params;
    }

    @Override
    public boolean updateEntity(Ticket updatedEntity) throws Exception {
        return false;
    }
}