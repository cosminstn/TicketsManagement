package com.stn.tickets.forms;

public interface IEntityForm<T> {

    T getEntityFromForm() throws Exception;

    void loadEntityIntoForm(T entity) throws Exception;
}
