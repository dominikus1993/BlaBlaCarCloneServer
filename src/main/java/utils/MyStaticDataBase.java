package utils;

import com.google.common.collect.ImmutableList;
import entities.Person;
import entities.Ride;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by domin_000 on 29.12.2015.
 */
public class MyStaticDataBase {
    private final ImmutableList<Person> persons;
    private final ImmutableList<Ride> rides;

    public MyStaticDataBase(List<Person> persons, List<Ride> rides) {
        this.persons = ImmutableList.copyOf(persons);
        this.rides = ImmutableList.copyOf(rides);
    }

    public ImmutableList<Person> getPersons() {
        return persons;
    }

    public ImmutableList<Ride> getRides() {
        return rides;
    }


}
