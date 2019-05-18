package com.stn.tickets.enums;

public enum TicketTypes {
    VIP(1, "VIP"),
    STANDARD(2, "Standard"),
    FRONT_ROW(3, "Front row"),
    MIDDLE_ROW(4, "Middle row"),
    LAST_ROW(5, "Last row"),
    BACKSTAGE(6, "Backstage");

    private int id;
    private String name;

    TicketTypes(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static TicketTypes getTicketTypeById(int id) {
        for (TicketTypes t : TicketTypes.values())
            if (t.getId() == id)
                return t;
        return null;
    }
}