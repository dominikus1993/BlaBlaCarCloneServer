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
    private  List<Person> persons;
    private  List<Ride> rides;

    public MyStaticDataBase(List<Person> persons, List<Ride> rides) {
        this.persons = persons;
        this.rides = rides;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<Ride>  getRides() {
        return rides;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }
}
