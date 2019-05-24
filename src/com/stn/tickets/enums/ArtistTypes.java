package com.stn.tickets.enums;

public enum ArtistTypes {
    COMEDIAN(1, "Comediant"),
    PLAYWRIGHT(2, "Dramaturg"),
    ACTOR(3, "Actor"),
    SINGER(4, "Cantaret");

    private int id;
    private String name;

    ArtistTypes(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public static ArtistTypes getArtistTypeById(int id) {
        for (ArtistTypes type : ArtistTypes.values())
            if (type.getId() == id)
                return type;
        return null;
    }
}