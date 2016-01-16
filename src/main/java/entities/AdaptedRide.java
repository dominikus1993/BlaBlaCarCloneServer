package entities;

import com.google.common.collect.ImmutableList;
import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Created by domin_000 on 16.01.2016.
 */
public class AdaptedRide {
    private String from;

    private String to;

    private double price;

    private int id;

    private Person owner;

    private Date date;

    private int amountOfSeats;

    private ImmutableList<Person> persons;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
    }

    public void setAmountOfSeats(int amountOfSeats) {
        this.amountOfSeats = amountOfSeats;
    }

    public ImmutableList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ImmutableList<Person> persons) {
        this.persons = persons;
    }
}
