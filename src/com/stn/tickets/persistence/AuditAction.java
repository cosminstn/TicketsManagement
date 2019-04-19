package com.stn.tickets.persistence;

import com.stn.tickets.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditAction extends PersistentEntity {

    private String name;
    private Date timestamp;

    public static final String PERSISTENCE_FILE_NAME = "actions.csv";

    public AuditAction() {
        super(PERSISTENCE_FILE_NAME);
    }

    public AuditAction(String name, Date timestamp) {
        super(PERSISTENCE_FILE_NAME);
        this.name = name;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toCsvLine() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.CSV_DATE_TIME_FORMAT);

        return filterString(name) + "," + timestamp;
    }
}
