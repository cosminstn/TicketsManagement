package com.stn.tickets.utils;

import java.util.Objects;
import java.util.Properties;

public class ConfUtils {

    private String dbUrl;
    private String dbUser;
    private String dbPass;

    private boolean isConfValid;

    private static ConfUtils instance;

    private ConfUtils() {
        isConfValid = readConfigurationData();
    }

    public static ConfUtils getInstance() {
        if (instance == null)
            instance = new ConfUtils();
        return instance;
    }

    private boolean readConfigurationData() {
        try {
            final Properties properties = new Properties();
            properties.load(Objects.requireNonNull(
                    ConfUtils.class.getClassLoader().getResourceAsStream("project.properties")));

            dbUrl   = properties.getProperty("dbUrl").trim();
            dbUser  = properties.getProperty("dbUser").trim();
            dbPass  = properties.getProperty("dbPass").trim();

            if (dbUrl.length() == 0)
                throw new Exception("Database URL missing!");
            if (dbUser.length() == 0)
                throw new Exception("Database user missing!");
            if (dbPass.length() == 0)
                throw new Exception("Database user password missing!");

            return true;
        } catch (Exception ex) {
            System.out.println("Cannot read configuration data: ");
            ex.printStackTrace();
            return false;
        }
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPass() {
        return dbPass;
    }

    public boolean isConfValid() {
        return isConfValid;
    }
}
