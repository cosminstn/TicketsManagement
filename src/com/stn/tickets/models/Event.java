package com.stn.tickets.models;

import com.stn.tickets.persistence.PersistentEntity;
import com.stn.tickets.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public abstract class Event extends PersistentEntity {

    private Integer id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Location location;

    private static final String PERSISTENCE_FILE_NAME = "events.csv";

    public Event() {
        super(PERSISTENCE_FILE_NAME);
    }

    public Event(Integer id, String name, String description, Date startDate, Date endDate, Location location) {
        super(PERSISTENCE_FILE_NAME);
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public abstract List<Constants.TICKET_TYPES> getTicketsTypes();

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;

        if (!Event.class.isAssignableFrom(o.getClass()))
            return false;

        final Event event = (Event) o;
        return Objects.equals(event.getId(), this.getId());
    }

    @Override
    public String toCsvLine() throws Exception {
//        private Integer id;
//        private String name;
//        private String description;
//        private Date startDate;
//        private Date endDate;
//        private Location location;
        String line = "";
        if (id == null)
            throw new Exception("Id can't be null on a persistent entity");
        line += id.toString() + ",";

        line += (name != null ? name : "") + ",";
        line += (description != null ? description : "") + ",";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        line += (startDate != null ? dateFormat.format(startDate) : "") + ",";
        line += (endDate != null ? dateFormat.format(endDate) : "") + ",";

        line += (location.getId() != null ? location.getId() : "");
        return line;
    }
}
