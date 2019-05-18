package com.stn.tickets.services;

import com.stn.tickets.db.dao.models.Location;
import com.stn.tickets.persistence.PersistenceService;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LocationsService {

//    private TreeSet<Location> locations;
//
//    private PersistenceService<Location> persistenceService;
//    private static LocationsService instance = new LocationsService();
//
//    public static LocationsService getInstance() {
//        return instance;
//    }
//
//    private LocationsService() {
//
//        locations = new TreeSet<>(new LocationsComparator());
//        persistenceService = new PersistenceService<>(new Location());
//        List<Location> locsList = persistenceService.loadPersistentList();
//        locations.addAll(locsList);
//    }
//
//    public List<Location> getAllLocations() {
//        return new ArrayList<>(locations);
//    }
//
//    public List<Location> getLocationsByCity(String cityName) throws Exception {
//        List<Location> cityLocations = new ArrayList<>();
//        for (Location loc : locations)
//            if (loc.getCity().equalsIgnoreCase(cityName.trim()))
//                cityLocations.add(loc);
//        if (cityLocations.size() == 0)
//            throw new Exception("No locations found for this city!");
//        return cityLocations;
//    }
//
//    public List<Location> getLocationsByCountry(String countryName) throws Exception {
//        List<Location> countryLocations = new ArrayList<>();
//        for (Location loc : locations)
//            if (loc.getCountry().equalsIgnoreCase(countryName.trim()))
//                countryLocations.add(loc);
//        if (countryLocations.size() == 0)
//            throw new Exception("No locations found for this country!");
//        return countryLocations;
//    }
//
//    public Location getRandomLocation() throws Exception {
//        int randomPosition = ThreadLocalRandom.current().nextInt(0, locations.size());
//        int currentIndex = 0;
//        for (Location loc : locations) {
//            if (currentIndex == randomPosition)
//                return loc;
//            currentIndex++;
//        }
//        throw new Exception("Could not generate random location!");
//    }
//
//    public Location getLocationById(int id) throws Exception {
//        for (Location loc : locations)
//            if (loc.getId() == id)
//                return loc;
//        throw new Exception ("No location found for this id");
//    }
//
//    private class LocationsComparator implements Comparator<Location> {
//        @Override
//        public int compare(Location l1, Location l2) {
//            try {
//                return l1.getCountry().compareTo(l2.getCountry()) == 0 ? (
//                        l1.getCity().compareTo(l2.getCity()) == 0 ?
//                                l1.getName().compareTo(l2.getName()) : l1.getCity().compareTo(l2.getCity())) :
//                        l1.getCountry().compareTo(l2.getCountry()) ;
//            } catch (Exception ex) {
//                return 0;
//            }
//        }
//    }
//
//    public void registerLocation(Location loc) throws Exception {
//        if (loc.getId() == null)
//            throw new Exception("Null id");
//
//        locations.add(loc);
//    }
//
//    public boolean persistData() {
//        try {
//            List<Location> locsList = new ArrayList<>(locations);
//
//            persistenceService.persistList(locsList);
//            return false;
//        } catch (Exception ex) {
//            System.out.println("Could not persist locations");
//            ex.printStackTrace();
//            return false;
//        }
//    }
}
