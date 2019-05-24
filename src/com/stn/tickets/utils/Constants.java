package com.stn.tickets.utils;

import java.io.File;

public class Constants {

    public static final String COMMA_REPLACER = "`";
    public static final String CSV_FIELD_SEPARATOR = ",";
    public static final String CSV_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String PERSISTENCE_FILES_FOLDER = System.getProperty("user.dir") +
            File.separator + "PERSISTENCE_FILES";
    public static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    public static final String APP_NAME = "Tickets Management Platform";
    public static final String STANDARD_DATE_FORMAT = "dd-MM-yyyy";
    public static final String STANDARD_DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
    public static final boolean AUTO_LOGIN = true;

}
