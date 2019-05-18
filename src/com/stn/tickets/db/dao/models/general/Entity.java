package com.stn.tickets.db.dao.models.general;

public abstract class Entity {

    protected Integer id;

    public Entity() {}

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
