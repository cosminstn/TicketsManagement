package com.stn.tickets.db.dao.services.general;

import com.stn.tickets.db.dao.models.general.Entity;
import com.stn.tickets.db.engine.DbEngine;
import com.stn.tickets.db.engine.PreparedStatementParameter;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public abstract class EntityDAO<T extends Entity> implements IEntityDAO<T> {

    protected String tableName;
    protected String primaryKeyName;
    protected boolean pkInherited;

    public EntityDAO(String tableName, String primaryKeyName, boolean pkInherited) {
        this.tableName = tableName;
        this.primaryKeyName = primaryKeyName;
        this.pkInherited = pkInherited;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

    public boolean isPkInherited() {
        return pkInherited;
    }

    public void setPkInherited(boolean pkInherited) {
        this.pkInherited = pkInherited;
    }

    public abstract List<String> getColumnsNamesWithoutPK();

    public List<String> getColumnsNames(boolean includePK) {
        if (!includePK)
            return getColumnsNamesWithoutPK();
        List<String> cols = new ArrayList<>();
        cols.add(primaryKeyName);
        cols.addAll(getColumnsNamesWithoutPK());

        return cols;
    }

    protected String getInsertSql() {
        List<String> cols = getColumnsNames(isPkInherited());
        return "insert into " + tableName + getColumnsSqlPart(isPkInherited()) + " values "
                + getParamsPlaceHolder(cols.size());
    }

    protected String getUpdateSql() {
        List<String> cols = getColumnsNames(false);
        String sql = "update " + tableName + " set ";
        int index = 0;
        for (String col : cols) {
            sql +=  col + " = ? ";
            if (index < cols.size() - 1)
                sql += " and ";
        }
        //condition
        sql += " where " + primaryKeyName + " = ? ";
        return sql;
    }


    /**
     * Arranges the columns like this (COL1, COL2, COL3)
     * @param includePK Tells the function to include the primary key or not into the sequence
     * @return
     */
    private String getColumnsSqlPart(boolean includePK) {
        List<String> cols = getColumnsNames(includePK);

        StringBuilder sqlPart = new StringBuilder("(");
        for (String col : cols) {
            sqlPart.append(col).append(", ");
        }
        int lastSeparatorIndex = sqlPart.lastIndexOf(", ");
        sqlPart.delete(lastSeparatorIndex, lastSeparatorIndex + 2);
        sqlPart.append(")");
        return sqlPart.toString();
    }

    private String getParamsPlaceHolder(int paramsCount) {
        StringBuilder paramsPart = new StringBuilder("(");
        for (int p = 0; p < paramsCount; p++) {
            paramsPart.append("?").append(", ");
        }
        int lastSeparatorIndex = paramsPart.lastIndexOf(", ");
        paramsPart.delete(lastSeparatorIndex, lastSeparatorIndex + 2);
        paramsPart.append(")");
        return paramsPart.toString();
    }

    protected String getSelectEntityByIdSql() {
        return "select * from " + tableName + " where " + primaryKeyName + " = ?";
    }

    public Object[] getUnparsedEntityById(int id) throws Exception {
        List<PreparedStatementParameter> params = new ArrayList<>();
        params.add(new PreparedStatementParameter<Integer>(1, id, Types.INTEGER));

        return DbEngine.getInstance()
                .executeFetchFirstLine(getSelectEntityByIdSql(), params);
    }

    /**
     * Persists an entity batch
     * @param entityBatch The list of entities
     * @return Returns the number of entities registered into the database
     * @throws Exception sql or null exceptions
     */
    public int createEntityBatch(List<T> entityBatch) throws Exception {
        String sql = getInsertSql();
        List<List<PreparedStatementParameter>> paramsBatch = new ArrayList<>();

        for (T entity : entityBatch) {
            paramsBatch.add(castToParamsList(entity, false));
        }

        int[] inserts = DbEngine.getInstance().updateBatch(sql, paramsBatch);
        int count = 0;
        for (int i : inserts)
            count += (i == 1 ? 1 : 0);
        return count;
    }

    public List<T> getAllEntities() throws Exception {
        String sql = "select * from " + tableName;
        List<Object[]> unparsedList = DbEngine.getInstance().query(sql);

        List<T> finalResult = new ArrayList<>();
        for (Object[] obj : unparsedList) {
            finalResult.add(castFromObjectArray(obj));
        }
        return finalResult;
    }

    public List<Object[]> getAllEntitiesUnparsed() throws Exception {
        String sql = "select * from " + tableName;
        return DbEngine.getInstance().query(sql);
    }

    @Override
    public boolean isEntityPersistent(int id) throws Exception {
        String sql = "select count(*) from " + tableName + " where " + primaryKeyName + " = ?";
        List<PreparedStatementParameter> params = new ArrayList<>();
        params.add(new PreparedStatementParameter<Integer>(1, id, Types.INTEGER));

        int count = (Integer) DbEngine.getInstance().executeScalar(sql, params);
        return count == 1;
    }

    @Override
    public int createEntity(T entity) throws Exception {
        String sql = getInsertSql();
        System.out.println(sql);
        List<PreparedStatementParameter> params = castToParamsList(entity, false);
        return DbEngine.getInstance().insert(sql, params);
    }

    @Override
    public T getEntity(int id) throws Exception {
        Object[] unparsedObject = getUnparsedEntityById(id);

        return castFromObjectArray(unparsedObject);
    }

    public boolean updateEntity(T entity) throws Exception {
        if (!isEntityPersistent(entity.getId()))
            throw new Exception("Entity is not persistent!");
        List<PreparedStatementParameter> params = castToParamsList(entity, false);

        params.add(new PreparedStatementParameter<Integer>(params.size() + 1, entity.getId(), Types.INTEGER));
        return (Integer) DbEngine.getInstance().update(getUpdateSql(), params) == 1;
    }

    @Override
    public boolean deleteEntity(int id) throws Exception {
        String sql = "delete from " + tableName + " where " + primaryKeyName + " = ?";
        List<PreparedStatementParameter> params = new ArrayList<>();
        params.add(new PreparedStatementParameter<Integer>(1, id, Types.INTEGER));
        return DbEngine.getInstance().update(sql, params) > 0;
    }

    // Casting
    public abstract T castFromObjectArray(Object[] arr) throws Exception;

    public abstract List<PreparedStatementParameter> castToParamsList(T entity, boolean includePK);
}
