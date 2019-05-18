package com.stn.tickets.dao.entities;

import com.stn.tickets.dao.general.Entity;

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
