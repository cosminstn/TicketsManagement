package com.stn.tickets.db.dao;

import com.stn.tickets.db.dao.general.*;

public abstract class EntityDAO<T extends Entity> implements IEntityDAO<T> {

    private String tableName;
    private String primaryKeyName;

    public EntityDAO(String tableName, String primaryKeyName) {
        this.tableName = tableName;
        this.primaryKeyName = primaryKeyName;
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

    @Override
    public boolean isEntityPersistent(int id) throws Exception {
        String sql = "select count(*) from " + tableName + " where " + primaryKeyName + " = ?";
        List<>
    }
}
