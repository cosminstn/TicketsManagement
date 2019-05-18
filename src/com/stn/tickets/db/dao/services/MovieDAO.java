package com.stn.tickets.db.dao.services;

import com.stn.tickets.db.dao.models.Movie;
import com.stn.tickets.db.dao.services.general.EntityDAO;
import com.stn.tickets.db.engine.PreparedStatementParameter;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieDAO extends EntityDAO<Movie> {

    private static MovieDAO instance;

    private MovieDAO() {
        super("MOVIES", "EVENT_ID", true);
    }

    @Override
    public List<String> getColumnsNamesWithoutPK() {
        return Arrays.asList("MINIMUM_AGE", "BIGGEST_STAR_ID", "DURATION_MIN");
    }

    @Override
    public Movie castFromObjectArray(Object[] arr) throws Exception {
        int colIndex = 0;

        Movie movie;

        int evId = (Integer) arr[colIndex++];
        movie = new Movie(EventDAO.getInstance().getEntity(evId));
        movie.setMinimumAge((Integer) arr[colIndex++]);

        int biggestStarId = (Integer) arr[colIndex++];
        movie.setBiggestStar(ArtistDAO.getInstance().getEntity(biggestStarId));
        movie.setDurationInMinutes((Integer) arr[colIndex]);
        return movie;
    }

    @Override
    public List<PreparedStatementParameter> castToParamsList(Movie entity, boolean includePK) {
        List<PreparedStatementParameter> params = new ArrayList<>();

        int colIndex = 1;
        if (includePK)
            params.add(new PreparedStatementParameter<Integer>(colIndex++, entity.getId(), Types.INTEGER));
        params.add(new PreparedStatementParameter<Integer>(colIndex++, entity.getMinimumAge(), Types.INTEGER));
        params.add(new PreparedStatementParameter<Integer>(colIndex++, entity.getBiggestStar().getId(), Types.INTEGER));
        params.add(new PreparedStatementParameter<Integer>(colIndex, entity.getDurationInMinutes(), Types.INTEGER));

        return params;
    }

    @Override
    public boolean updateEntity(Movie updatedEntity) throws Exception {
        return false;
    }
}
