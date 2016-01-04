package entities;

import java.util.Date;

/**
 * Created by dominik.kotecki on 04-01-2016.
 */
public class UpdateRide {
    private final int id;
    private final Person owner;
    private final String from;
    private final String to;
    private final double price;
    private final Date date;
    private final int amountOfSeats;

    public UpdateRide(int id, Person owner, String from, String to, double price, Date date, int amountOfSeats) {
        this.id = id;
        this.owner = owner;
        this.from = from;
        this.to = to;
        this.price = price;
        this.date = date;
        this.amountOfSeats = amountOfSeats;
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

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
    }
}
