package com.stn.tickets.db.dao.models;

import com.stn.tickets.db.dao.models.general.Entity;
import com.stn.tickets.enums.ArtistTypes;
import com.stn.tickets.utils.Constants;

import java.sql.Date;

public class Artist extends Entity {

    private String name;
    private String profilePicUrl;
    private java.sql.Date birthDate;
    private ArtistTypes type;

    public Artist() {}

    public Artist(Integer id, String name, String profilePicUrl, Date birthDate, ArtistTypes type) {
        super(id);
        this.name = name;
        this.profilePicUrl = profilePicUrl;
        this.birthDate = birthDate;
        this.type = type;
    }

    public Artist(String name, String profilePicUrl, Date birthDate, ArtistTypes type) {
        this.name = name;
        this.profilePicUrl = profilePicUrl;
        this.birthDate = birthDate;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public ArtistTypes getType() {
        return type;
    }

    public void setType(ArtistTypes type) {
        this.type = type;
    }
}
