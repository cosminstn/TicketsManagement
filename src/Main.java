import com.stn.tickets.models.Consumer;
import com.stn.tickets.services.ConsumersService;
import com.stn.tickets.services.TicketsService;
import com.stn.tickets.utils.Constants;
import com.stn.tickets.models.Location;
import com.stn.tickets.models.Movie;
import com.stn.tickets.services.EventsService;
import com.stn.tickets.services.LocationsService;
import com.stn.tickets.utils.Utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static EventsService eventsService       = EventsService.getInstance();
    private static LocationsService locationsService = LocationsService.getInstance();
    private static TicketsService ticketsService     = TicketsService.getInstance();
    private static ConsumersService consumersService = ConsumersService.getInstance();


    public static void main(String[] args) throws Exception {

        Location myLocation = locationsService.getRandomLocation();
        System.out.println("I'm here: " + myLocation);

        // Create an event and configure it's tickets
        for (int i = 0; i < 50; i++) {
            Movie newMovie = Utils.createRandomMovie();
            try {
                eventsService.registerEvent(newMovie);
                eventsService.createEventTickets(newMovie, Constants.TICKET_TYPES.STANDARD,
                        Math.abs(ThreadLocalRandom.current().nextInt() % 100) + 1, 60);
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

        //create demo consumers and register them
        List<Consumer> fakeConsumers = Utils.createDemoConsumers(50);
        for (Consumer c : fakeConsumers) {
            try {
                consumersService.registerConsumer(c);
            } catch (Exception ex) {
                System.out.println("Could not register consumer: " + c.getFirstName());
                ex.printStackTrace();
            }
        }

        persistAll();
    }

    private static void persistAll() {
        ticketsService.persistData();
        consumersService.persistData();
        eventsService.persistData();
        locationsService.persistData();
    }
}
