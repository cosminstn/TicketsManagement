package com.stn.tickets.utils;

public class Utils {

//    private static EventsService eventsService = EventsService.getInstance();
//    private static LocationsService locationsService = LocationsService.getInstance();
//
//    private static String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789";
//    private static String LOWERCASE_CHARS = UPPERCASE_CHARS.toLowerCase();
//
//    public static Movie createRandomMovie() throws Exception {
//        return (Movie) createRandomEvent(Movie.class);
//    }
//
//    public static StandupComedyShow createRandomStandupShow() throws Exception {
//        return (StandupComedyShow) createRandomEvent(StandupComedyShow.class);
//    }
//
//    public static Concert createRandomConcert() throws Exception {
//        return (Concert) createRandomEvent(Concert.class);
//    }
//
//    public static TheatrePlay createRandomTheatrePlay() throws Exception {
//        return (TheatrePlay) createRandomEvent(TheatrePlay.class);
//    }
//
//    private static Event createRandomEvent(Class<? extends Event> evClass) throws Exception {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new java.util.Date());
//        cal.add(Calendar.DATE, Math.abs(ThreadLocalRandom.current().nextInt()) % 200);
//        cal.set(Calendar.HOUR_OF_DAY, 16 + Math.abs(ThreadLocalRandom.current().nextInt()) % 5);
//        cal.set(Calendar.MINUTE, 30);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//        Event event;
//        if (evClass == Movie.class)
//            event = new Movie();
//        else if (evClass == StandupComedyShow.class)
//            event = new StandupComedyShow();
//        else if (evClass == TheatrePlay.class)
//            event = new TheatrePlay();
//        else
//            event = new Concert();
//
//        event.setLocation(locationsService.getRandomLocation());
//        event.setName(generateString(UPPERCASE_CHARS, 20));
//        event.setDescription(generateString(LOWERCASE_CHARS, 60));
//        event.setStartDate(cal.getTime());
//        event.setEndDate(cal.getTime());
//        return event;
//    }
//
//    private static String generateString(String characters, int length)
//    {
//        char[] text = new char[length];
//        for (int i = 0; i < length; i++)
//        {
//            text[i] = characters.charAt(ThreadLocalRandom.current().nextInt(characters.length()));
//        }
//        return new String(text);
//    }
//
//    public static List<Consumer> createDemoConsumers(int count) {
//        List<Consumer> fakeConsumers = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            fakeConsumers.add(new Consumer(i, "John", "Doe" + i, new Date(),
//                    "city" + i, "country" + i, "email" + i));
//        }
//        return fakeConsumers;
//    }
//
//    public static List<Location> createLocations(int count) {
//        List<Location> fakeLocs = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            Location loc = new Location();
//            loc.setId(i);
//            loc.setCity("City" + i);
//            loc.setName("Name" + i);
//            loc.setCountry("Country" + i);
//            fakeLocs.add(loc);
//        }
//        return fakeLocs;
//    }

}
