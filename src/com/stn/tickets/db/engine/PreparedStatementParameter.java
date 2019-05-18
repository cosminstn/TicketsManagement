package com.stn.tickets.db.engine;

import java.sql.PreparedStatement;
import java.sql.Types;

public class PreparedStatementParameter<T> {

    private int index;
    private T value;
    private int sqlType;

    public PreparedStatementParameter() {
    }

    public PreparedStatementParameter(int index, T value, int sqlType) {
        this.index = index;
        this.value = value;
        this.sqlType = sqlType;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    @Deprecated
    public static void applyParameter(PreparedStatement prepStmt, PreparedStatementParameter param) throws Exception {
        if (param.getSqlType() == Types.INTEGER)
            prepStmt.setInt(param.getIndex(), (Integer) param.getValue());
        else if (param.getSqlType() == Types.NVARCHAR || param.getSqlType() == Types.VARCHAR)
            prepStmt.setString(param.getIndex(), (String) param.getValue());
        else
            prepStmt.setObject(param.getIndex(), param.getValue());
    }

}
