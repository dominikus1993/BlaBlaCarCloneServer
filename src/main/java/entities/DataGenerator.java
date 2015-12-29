package entities;

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
        persons.add(new Person(Person.getIdentityId(), "Dominiki", "Kotecki","dominikus1@gmail.com" ,"password"));
        persons.add(new Person(Person.getIdentityId(), "Dominiki1", "Kotecki1","dominikus2@gmail.com" ,"password"));
        persons.add(new Person(Person.getIdentityId(), "Dominiki2", "Kotecki2","dominikus3@gmail.com" ,"password"));
        persons.add(new Person(Person.getIdentityId(), "Dominiki3", "Kotecki3","dominikus3@gmail.com" ,"password"));

        rides = new LinkedList<>();

    }
}
