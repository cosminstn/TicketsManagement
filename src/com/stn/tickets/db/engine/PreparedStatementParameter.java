package com.stn.tickets.dao.engine;

import java.sql.PreparedStatement;

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

    public static void applyParameter(PreparedStatement prepStmt, PreparedStatementParameter param) throws Exception {
        prepStmt.setObject(param.getIndex(), param.getValue(), param.getSqlType());
    }

}
