import com.stn.tickets.models.Constants;
import com.stn.tickets.models.Location;
import com.stn.tickets.models.Movie;
import com.stn.tickets.services.EventsService;
import com.stn.tickets.services.LocationsService;
import com.stn.tickets.utils.Utils;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static EventsService eventsService       =  EventsService.getInstance();
    private static LocationsService locationsService = LocationsService.getInstance();

    public static void main(String[] args) throws Exception {

        Location myLocation = locationsService.getRandomLocation();
        System.out.println("I'm here: " + myLocation);

        // Create an event and configure it's tickets
        for (int i = 0; i < 50; i++) {
            Movie newMovie = Utils.createRandomMovie();
            try {
                eventsService.registerEvent(newMovie);
                eventsService.createEventTickets(newMovie, Constants.TICKET_TYPES.STANDARD,
                        Math.abs(ThreadLocalRandom.current().nextInt() % 100) + 1, 6000);
            } catch (Exception ex) {
                System.out.println("Could not register movie: " + newMovie.getName());
                ex.printStackTrace();
            }
        }

        // print all movies
        System.out.println("All movies: ");
        for (Movie movie : eventsService.getAllMovies())
            System.out.println(movie.toString());

        System.out.println("All locations: ");
        for (Location loc : locationsService.getAllLocations())
            System.out.println(loc.toString());

        System.out.println("Movies near you: ");
        try {
            for (Movie movie : eventsService.getMoviesByCity(myLocation.getCity()))
                System.out.println(movie);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Cool movies in your country: ");
        try {
            for (Movie movie : eventsService.getMoviesByCountry(myLocation.getCountry()))
                System.out.println(movie);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
