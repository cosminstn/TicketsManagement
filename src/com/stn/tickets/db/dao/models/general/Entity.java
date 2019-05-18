package com.stn.tickets.db.dao.general;

public abstract class Entity {

    protected Integer id;

    public Entity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
