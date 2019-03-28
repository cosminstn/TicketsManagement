package com.stn.tickets.services;

import com.stn.tickets.interfaces.IEventService;
import com.stn.tickets.models.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventsService implements IEventService {

    private List<Event> events;
    private Integer nextEventId = 0;
    private TicketsService ticketsService;
    private LocationsService locationsService;

    private static EventsService instance = new EventsService();

    public static EventsService getInstance() {
        return instance;
    }

    private EventsService() {
        events              = new ArrayList<>();
        ticketsService      = TicketsService.getInstance();
        locationsService    = LocationsService.getInstance();
    }

    public Movie createMovie(String name, String description, Date startDate, Date endDate, Location location) {
        Movie newMovie = new Movie();
        newMovie.setId(getNextEventId());
        newMovie.setName(name);
        newMovie.setDescription(description);
        newMovie.setStartDate(startDate);
        newMovie.setEndDate(endDate);
        newMovie.setLocation(location);

        events.add(newMovie);
        return newMovie;
    }

    /**
     * Registers an event into the service.
     * @param event The event to be registered.
     * @throws Exception Throws an exception if the event is already registered in the service.
     */
    public void registerEvent(Event event) throws Exception {
        if (event.getId() != null && isEventRegistered(event))
            throw new Exception("This event is already registered");
        event.setId(getNextEventId());
        events.add(event);
    }

    public boolean createEventTickets(Event ev, Constants.TICKET_TYPES ticketType, double price, int count) throws Exception {
        if (!isEventRegistered(ev))
            throw new Exception ("This event is not registered!");
        ticketsService.createTickets(ev, ticketType, price, count);
        return true;
    }

    private boolean isEventRegistered(Event ev) {
        for (Event registeredEvent : events)
            if (ev.equals(registeredEvent))
                return true;
        return false;
    }


    private int getNextEventId() {
        return nextEventId++;
    }

    private HashMap<Class<? extends Event>, List<Ticket>> eventTickets;

    @Override
    public Ticket buyTicket(Consumer consumer, Constants.TICKET_TYPES type, String promoCode) {
        return null;
    }

    @Override
    public List<Ticket> getAvailableTicketsByType(Class<? extends Event> ev, Constants.TICKET_TYPES type) {
        return null;
    }

    @Override
    public List<Ticket> getAvailableTickets(Class<? extends Event> ev) {
        return null;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        for (Event ev : events)
            if (ev instanceof Movie)
                movies.add((Movie) ev);
        return movies;
    }

    private List<Event> getEventsByLocations(List<Location> locations) throws Exception {
        List<Event> locsEvents = new ArrayList<>();
        for (Event ev : events) {
            for (Location loc : locations)
                if (loc.equals(ev.getLocation())) {
                    locsEvents.add(ev);
                    break;
                }
        }
        if (locsEvents.size() == 0)
            throw new Exception("Nothing found for these filters!");
        return locsEvents;
    }

    private List<Event> getEventsByCountry(String countryName) throws Exception {
        List<Location> locs = locationsService.getLocationsByCountry(countryName);
        return getEventsByLocations(locs);
    }

    private List<Event> getEventsByCity(String cityName) throws Exception {
        List<Location> locs = locationsService.getLocationsByCity(cityName);
        return getEventsByLocations(locs);
    }

    public List<Movie> getMoviesByCountry(String countryName) throws Exception {
        List<Movie> countryMovies = new ArrayList<>();
        for (Event ev : getEventsByCountry(countryName))
            if (ev instanceof Movie)
                countryMovies.add((Movie) ev);
        if (countryMovies.size() == 0)
            throw new Exception("No movie found for this country");
        return countryMovies;
    }

    public List<Movie> getMoviesByCity(String city) throws Exception {
        List<Movie> cityMovies = new ArrayList<>();
        for (Event ev : getEventsByCity(city))
            if (ev instanceof Movie)
                cityMovies.add((Movie) ev);
        if (cityMovies.size() == 0)
            throw new Exception("No movie found for this city");
        return cityMovies;
    }


}
