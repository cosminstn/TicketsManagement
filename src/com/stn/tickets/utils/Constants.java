package com.stn.tickets.utils;

import java.io.File;

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

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static final String COMMA_REPLACER = "`";
    public static final String CSV_FIELD_SEPARATOR = ",";
    public static final String CSV_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String PERSISTENCE_FILES_FOLDER = System.getProperty("user.dir") +
            File.separator + "PERSISTENCE_FILES";
}
