package entities;

import com.google.common.collect.ImmutableList;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dominik.kotecki on 28-12-2015.
 */
public class Ride {
    private static AtomicInteger identity = new AtomicInteger(0);
    private final int id;
    private final Person owner;
    private final String from;
    private final String to;
    private final double price;
    private final Date date;
    private final int amountOfSeats;
    private final ImmutableList<Person> persons;


    public Ride(int id, Person owner, String from, String to, double price, Date date, int amountOfSeats, List<Person> persons) {
        this.id = id;
        this.owner = owner;
        this.from = from;
        this.to = to;
        this.price = price;
        this.date = date;
        this.amountOfSeats = amountOfSeats;
        this.persons = ImmutableList.copyOf(persons);
    }

    public static int getIdentityId(){
        return identity.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public Person getOwner() {
        return owner;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Date getDate() {
        return date;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
    }

    public List<Person> getPersons() {
        return persons;
    }

}
