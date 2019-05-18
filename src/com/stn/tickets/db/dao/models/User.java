package com.stn.tickets.db.dao.models;

import com.stn.tickets.db.dao.models.general.Entity;

public class User extends Entity {

    private String username;
    private String password;

    public User(Integer id, String username, String password) {
        super(id);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
