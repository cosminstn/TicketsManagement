package com.stn.tickets.utils;

import java.io.File;

public class Constants {

    public static final String COMMA_REPLACER = "`";
    public static final String CSV_FIELD_SEPARATOR = ",";
    public static final String CSV_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String PERSISTENCE_FILES_FOLDER = System.getProperty("user.dir") +
            File.separator + "PERSISTENCE_FILES";
    public static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";

}
