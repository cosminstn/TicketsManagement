package com.stn.tickets.models;

public class Constants {

    public enum TICKET_TYPES {
        VIP(1, "VIP"),
        STANDARD(2, "Standard"),
        FRONT_ROW(3, "Front row"),
        MIDDLE_ROW(4, "Middle row"),
        LAST_ROW(5, "Last row"),
        BACKSTAGE(6, "Backstage");

        private int id;
        private String name;

        TICKET_TYPES(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

}
