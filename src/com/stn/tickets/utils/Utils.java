package com.stn.tickets.utils;

import com.stn.tickets.models.Movie;
import com.stn.tickets.services.EventsService;
import com.stn.tickets.services.LocationsService;

import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    private static EventsService eventsService = EventsService.getInstance();
    private static LocationsService locationsService = LocationsService.getInstance();

    private static String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789";
    private static String LOWERCASE_CHARS = UPPERCASE_CHARS.toLowerCase();

    public static Movie createRandomMovie() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date());
        cal.add(Calendar.DATE, ThreadLocalRandom.current().nextInt());
        cal.set(Calendar.HOUR_OF_DAY, 20);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Movie newMovie = new Movie();
        newMovie.setLocation(locationsService.getRandomLocation());
        newMovie.setName(generateString(UPPERCASE_CHARS, 20));
        newMovie.setDescription(generateString(LOWERCASE_CHARS, 60));
        newMovie.setStartDate(cal.getTime());
        newMovie.setEndDate(cal.getTime());
        return newMovie;
    }

    public static String generateString(String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(ThreadLocalRandom.current().nextInt(characters.length()));
        }
        return new String(text);
    }

}
