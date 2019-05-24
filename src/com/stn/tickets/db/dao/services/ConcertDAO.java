package com.stn.tickets.db.dao.services;

import com.stn.tickets.db.dao.models.Concert;
import com.stn.tickets.db.dao.models.Event;
import com.stn.tickets.db.dao.services.general.EntityDAO;
import com.stn.tickets.db.engine.DbEngine;
import com.stn.tickets.db.engine.PreparedStatementParameter;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcertDAO extends EntityDAO<Concert> {

    private static ConcertDAO instance;

    private ConcertDAO() {
        super("CONCERTS", "EVENT_ID", true);
    }

    public static ConcertDAO getInstance() {
        if (instance == null)
            instance = new ConcertDAO();
        return instance;
    }

    @Override
    public List<String> getColumnsNamesWithoutPK() {
        return Arrays.asList("IS_EXPLICIT", "MAIN_ARTIST_ID", "OPENING_ARTIST_ID");
    }

    @Override
    public Concert castFromObjectArray(Object[] arr) throws Exception {
        int colIndex = 0;

        Concert concert;

        int evId = (Integer) arr[colIndex++];
        concert = new Concert(EventDAO.getInstance().getEntity(evId));

        concert.setExplicit((boolean) arr[colIndex++]);
        int mainArtistId = (int) arr[colIndex++];
        concert.setMainArtist(ArtistDAO.getInstance().getEntity(mainArtistId));

        Integer openingArtistId = (Integer) arr[colIndex];
        if (openingArtistId != null) {
            concert.setOpeningArtist(ArtistDAO.getInstance().getEntity(openingArtistId));
        }
        return concert;
    }

    @Override
    public int createEntity(Concert con) throws Exception {
        // create the event
        int evId = EventDAO.getInstance().createEntity(con);
        con.setId(evId);

        int inserted = DbEngine.getInstance().update(getInsertSql(), castToParamsList(con, true));
        if (inserted != 1)
            throw new Exception("Could not create concert!");
        return evId;
    }

    @Override
    public List<PreparedStatementParameter> castToParamsList(Concert entity, boolean includePK) {
        List<PreparedStatementParameter> params = new ArrayList<>();

        int colIndex = 1;
        if (includePK)
            params.add(new PreparedStatementParameter<Integer>(colIndex++, entity.getId(), Types.INTEGER));
        params.add(new PreparedStatementParameter<Boolean>(colIndex++, entity.isExplicit(), Types.BOOLEAN));
        params.add(new PreparedStatementParameter<Integer>(colIndex++, entity.getMainArtist().getId(), Types.INTEGER));
        params.add(new PreparedStatementParameter<Integer>(colIndex,
                entity.getOpeningArtist() != null ? entity.getOpeningArtist().getId() : null, Types.INTEGER));

        return params;
    }

    @Override
    public boolean updateEntity(Concert updatedEntity) throws Exception {
        return false;
    }
}
