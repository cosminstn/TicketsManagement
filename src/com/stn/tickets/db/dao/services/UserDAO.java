package com.stn.tickets.db.dao.services;

import com.stn.tickets.db.dao.models.User;
import com.stn.tickets.db.dao.services.general.EntityDAO;
import com.stn.tickets.db.engine.PreparedStatementParameter;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO extends EntityDAO<User> {

    private static UserDAO instance;

    private UserDAO() {
        super("USERS", "USER_ID", false);
    }

    public static UserDAO getInstance() {
        if (instance == null)
            instance = new UserDAO();
        return instance;
    }

    @Override
    public List<String> getColumnsNamesWithoutPK() {
        return Arrays.asList("USERNAME", "PASSWORD");
    }

    @Override
    public boolean updateEntity(User udpatedEntity) throws Exception {
        return false;
    }

    @Override
    public boolean deleteEntity(int id) throws Exception {
        return false;
    }

    @Override
    public User castFromObjectArray(Object[] arr) throws Exception {
        int colIndex = 0;
        return new User((Integer) arr[colIndex++],
                (String) arr[colIndex++],
                (String) arr[colIndex]);
    }

    @Override
    public List<PreparedStatementParameter> castToParamsList(User entity, boolean includePK) {
        List<PreparedStatementParameter> params = new ArrayList<>();
        int colIndex = 1;

        if (includePK)
            params.add(new PreparedStatementParameter<>(colIndex++, entity.getId(), Types.INTEGER));
        params.add(new PreparedStatementParameter<>(colIndex++, entity.getUsername(), Types.NVARCHAR));
        params.add(new PreparedStatementParameter<>(colIndex, entity.getPassword(), Types.NVARCHAR));

        return params;
    }
}
