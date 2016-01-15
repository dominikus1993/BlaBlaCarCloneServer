package entities;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by domin_000 on 28.12.2015.
 */
public class DataGenerator {
    public static final List<Person> persons;
    public static final List<Ride> rides;

    static {
        persons = new LinkedList<>();
        persons.add(new Person(Person.getIdentityId(), "Dominiki", "Kotecki", "dominikus1@gmail.com", "password"));
        persons.add(new Person(Person.getIdentityId(), "Dominiki1", "Kotecki1", "dominikus2@gmail.com", "password"));
        persons.add(new Person(Person.getIdentityId(), "Dominiki2", "Kotecki2", "dominikus3@gmail.com", "password"));
        persons.add(new Person(Person.getIdentityId(), "Dominiki3", "Kotecki3", "dominikus3@gmail.com", "password"));

        rides = new LinkedList<>();
        rides.add(new Ride(Ride.getIdentityId(), persons.get(0), "Warszawa", "Kielce", 21.1, new Date(), 5));
        rides.add(new Ride(Ride.getIdentityId(), persons.get(1), "Lódź", "Katowice", 11, new Date(), 2));
        rides.add(new Ride(Ride.getIdentityId(), persons.get(2), "Lódź", "Stryków", 111, new Date(), 2));
    }
}
