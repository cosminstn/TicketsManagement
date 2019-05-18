package com.stn.tickets.db.dao.services;

import com.stn.tickets.db.dao.models.Event;
import com.stn.tickets.db.dao.models.Location;
import com.stn.tickets.db.dao.services.general.EntityDAO;
import com.stn.tickets.db.engine.PreparedStatementParameter;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("Duplicates")
public class EventDAO extends EntityDAO<Event> {

    private static EventDAO instance;

    private EventDAO() {
        super("EVENTS", "EVENT_ID", false);
    }

    public static EventDAO getInstance() {
        if (instance == null)
            instance = new EventDAO();
        return instance;
    }

    @Override
    public List<String> getColumnsNamesWithoutPK() {
        return Arrays.asList("NAME", "DESCRIPTION", "START_DATE", "END_DATE", "LOCATION_ID");
    }

    @Override
    public Event castFromObjectArray(Object[] arr) throws Exception {
        int colIndex = 0;

        Event event = new Event();
        event.setName((String) arr[colIndex++]);
        event.setDescription((String) arr[colIndex++]);
        event.setStartDate((java.sql.Date) arr[colIndex++]);
        event.setEndDate((java.sql.Date) arr[colIndex++]);

        int locId = (Integer) arr[colIndex];
        Location loc = LocationDAO.getInstance().getEntity(locId);
        event.setLocation(loc);
        return event;
    }

    @Override
    public List<PreparedStatementParameter> castToParamsList(Event entity, boolean includePK) {
        List<PreparedStatementParameter> params = new ArrayList<>();

        int colIndex = 1;
        if (includePK)
            params.add(new PreparedStatementParameter<Integer>(colIndex++, entity.getId(), Types.INTEGER));
        params.add(new PreparedStatementParameter<String>(colIndex++, entity.getName(), Types.NVARCHAR));
        params.add(new PreparedStatementParameter<String>(colIndex++, entity.getDescription(), Types.NVARCHAR));
        params.add(new PreparedStatementParameter<java.sql.Date>(colIndex++, entity.getStartDate(), Types.DATE));
        params.add(new PreparedStatementParameter<java.sql.Date>(colIndex++, entity.getEndDate(), Types.DATE));
        params.add(new PreparedStatementParameter<Integer>(colIndex, entity.getLocation().getId(), Types.INTEGER));

        return params;
    }

    @Override
    public boolean updateEntity(Event updatedEntity) throws Exception {
        return false;
    }
}
