package com.stn.tickets.db.dao.services;

import com.stn.tickets.db.dao.models.Consumer;
import com.stn.tickets.db.dao.services.general.EntityDAO;
import com.stn.tickets.db.engine.PreparedStatementParameter;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsumerDAO extends EntityDAO<Consumer> {

    private static ConsumerDAO instance;

    private ConsumerDAO() {
        super("CONSUMERS", "CONSUMER_ID", false);
    }

    public static ConsumerDAO getInstance() {
        if (instance == null)
            instance = new ConsumerDAO();
        return instance;
    }

    @Override
    public List<String> getColumnsNamesWithoutPK() {
        return Arrays.asList("FIRST_NAME", "LAST_NAME", "BIRTH_DATE", "CITY", "COUNTRY", "EMAIL");
    }

    @Override
    public Consumer castFromObjectArray(Object[] arr) throws Exception {
        int colIndex = 0;
        return new Consumer((Integer) arr[colIndex++],
                (String) arr[colIndex++],
                (String) arr[colIndex++],
                (java.sql.Date) arr[colIndex++],
                (String) arr[colIndex++],
                (String) arr[colIndex++],
                (String) arr[colIndex]);
    }

    @Override
    public List<PreparedStatementParameter> castToParamsList(Consumer entity, boolean includePK) {
        List<PreparedStatementParameter> params = new ArrayList<>();

        int colIndex = 1;
        if (includePK)
            params.add(new PreparedStatementParameter<Integer>(colIndex++, entity.getId(), Types.INTEGER));
        params.add(new PreparedStatementParameter<String>(colIndex++, entity.getFirstName(), Types.NVARCHAR));
        params.add(new PreparedStatementParameter<String>(colIndex++, entity.getLastName(), Types.NVARCHAR));
        params.add(new PreparedStatementParameter<java.sql.Date>(colIndex++, entity.getBirthDate(), Types.DATE));
        params.add(new PreparedStatementParameter<String>(colIndex++, entity.getCity(), Types.NVARCHAR));
        params.add(new PreparedStatementParameter<String>(colIndex++, entity.getCountry(), Types.NVARCHAR));
        params.add(new PreparedStatementParameter<String>(colIndex, entity.getEmail(), Types.NVARCHAR));

        return params;
    }

    @Override
    public boolean updateEntity(Consumer updatedEntity) throws Exception {
        return false;
    }
}
