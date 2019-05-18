package com.stn.tickets.db.dao.services;

import com.stn.tickets.db.dao.models.Location;
import com.stn.tickets.db.dao.services.general.EntityDAO;
import com.stn.tickets.db.engine.PreparedStatementParameter;
import com.stn.tickets.utils.ConfUtils;
import com.stn.tickets.utils.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationDAO extends EntityDAO<Location> {

    private static LocationDAO instance;

    private LocationDAO() {
        super("LOCATIONS", "LOCATION_ID", false);
    }

    public static LocationDAO getInstance() {
        if (instance == null)
            instance = new LocationDAO();
        return instance;
    }

    @Override
    public boolean updateEntity(Location updatedEntity) throws Exception {
        return false;
    }

    @Override
    public List<String> getColumnsNamesWithoutPK() {
        return Arrays.asList("NAME", "COUNTRY", "CITY");
    }

    public int tryToCreate(Location loc) throws Exception {
        String sql = getInsertSql();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(Constants.JDBC_DRIVER);
            conn = DriverManager.getConnection(ConfUtils.getInstance().getDbUrl(),
                    ConfUtils.getInstance().getDbUser(), ConfUtils.getInstance().getDbPass());

            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            for (PreparedStatementParameter param : params)
//                stmt.setObject(param.getIndex(), param.getValue(), param.getSqlType());

            stmt.setObject(1, loc.getName());
            stmt.setObject(2, loc.getCountry());
            stmt.setObject(3, loc.getCity());

            stmt.execute();
            rs = stmt.getGeneratedKeys();
            if (!rs.next())
                throw new Exception("empty reader!");
            return rs.getInt(1);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception ignored) {}
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception ignored) {}
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception ignored) {}
        }
    }

    @Override
    public boolean deleteEntity(int id) throws Exception {
        return false;
    }

    @Override
    public Location castFromObjectArray(Object[] arr) throws Exception {
        int colIndex = 0;
        return new Location((Integer) arr[colIndex++],
                (String) arr[colIndex++],
                (String) arr[colIndex++],
                (String) arr[colIndex]);
    }

    @Override
    public List<PreparedStatementParameter> castToParamsList(Location entity, boolean includePK) {
        List<PreparedStatementParameter> params = new ArrayList<>();

        int colIndex = 1;
        if (includePK)
            params.add(new PreparedStatementParameter<Integer>(colIndex++, entity.getId(), Types.INTEGER));
        params.add(new PreparedStatementParameter<String>(colIndex++, entity.getName(), Types.VARCHAR));
        params.add(new PreparedStatementParameter<String>(colIndex++, entity.getCountry(), Types.NVARCHAR));
        params.add(new PreparedStatementParameter<String>(colIndex, entity.getCity(), Types.NVARCHAR));

        return params;
    }
}
