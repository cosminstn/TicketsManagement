package com.stn.tickets.services;

import com.stn.tickets.models.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LocationsService {

    private List<Location> locations;

    private static LocationsService instance = new LocationsService();

    public static LocationsService getInstance() {
        return instance;
    }

    private LocationsService() {
        locations = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Location loc = new Location();
            loc.setId(0);
            loc.setName("Locatie" + i);
            loc.setCountry("Tara"+(i/8 + 1));
            loc.setCity("Oras" + i);
            locations.add(loc);
        }
    }

    public List<Location> getAllLocations() {
        return locations;
    }

    public List<Location> getLocationsByCity(String cityName) throws Exception {
        List<Location> cityLocations = new ArrayList<>();
        for (Location loc : locations)
            if (loc.getCity().equalsIgnoreCase(cityName.trim()))
                cityLocations.add(loc);
        if (cityLocations.size() == 0)
            throw new Exception("No locations found for this city!");
        return cityLocations;
    }

    public List<Location> getLocationsByCountry(String countryName) throws Exception {
        List<Location> countryLocations = new ArrayList<>();
        for (Location loc : locations)
            if (loc.getCountry().equalsIgnoreCase(countryName.trim()))
                countryLocations.add(loc);
        if (countryLocations.size() == 0)
            throw new Exception("No locations found for this country!");
        return countryLocations;
    }

    public Location getRandomLocation() {
        int randomPosition = ThreadLocalRandom.current().nextInt(0, locations.size());
        return locations.get(randomPosition);
    }

    public Location getLocationById(int id) throws Exception {
        for (Location loc : locations)
            if (loc.getId() == id)
                return loc;
        throw new Exception ("No location found for this id");
    }
}
