package entities;

import com.google.common.collect.ImmutableList;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dominik.kotecki on 28-12-2015.
 */
public class Ride implements Serializable{
    private static AtomicInteger identity = new AtomicInteger(0);

    @Expose
    private final int id;

    @Expose
    private final Person owner;

    @Expose
    private final String from;

    @Expose
    private final String to;

    @Expose
    private final double price;

    @Expose
    private final Date date;

    @Expose
    private final int amountOfSeats;

    @Expose
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

    public Ride(int id, Person owner, String from, String to, double price, Date date, int amountOfSeats) {
        this.id = id;
        this.owner = owner;
        this.from = from;
        this.to = to;
        this.price = price;
        this.date = date;
        this.amountOfSeats = amountOfSeats;
        this.persons = ImmutableList.copyOf(new LinkedList<>());
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

    public int getIdForNewEntity(){
        return identity.incrementAndGet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ride ride = (Ride) o;

        if (id != ride.id) return false;
        if (Double.compare(ride.price, price) != 0) return false;
        if (amountOfSeats != ride.amountOfSeats) return false;
        if (owner != null ? !owner.equals(ride.owner) : ride.owner != null) return false;
        if (from != null ? !from.equals(ride.from) : ride.from != null) return false;
        if (to != null ? !to.equals(ride.to) : ride.to != null) return false;
        if (date != null ? !date.equals(ride.date) : ride.date != null) return false;
        return persons != null ? persons.equals(ride.persons) : ride.persons == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + amountOfSeats;
        result = 31 * result + (persons != null ? persons.hashCode() : 0);
        return result;
    }
}
