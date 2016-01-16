package utils;

import com.google.common.collect.ImmutableList;
import entities.AdaptedPerson;
import entities.AdaptedRide;
import entities.Person;
import entities.Ride;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Random;

/**
 * Created by domin_000 on 16.01.2016.
 */
public class RideAdapter extends XmlAdapter<AdaptedRide, Ride> {

    @Override
    public Ride unmarshal(AdaptedRide v) throws Exception {
        return new Ride(v.getOwner(), v.getFrom(),v.getTo(),v.getPrice(),v.getDate(),v.getAmountOfSeats(),v.getPersons());
    }

    @Override
    public AdaptedRide marshal(Ride v) throws Exception {
        AdaptedRide adaptedRide = new AdaptedRide();
        adaptedRide.setId(v.getId());
        adaptedRide.setOwner(v.getOwner());
        adaptedRide.setFrom(v.getFrom());
        adaptedRide.setTo(v.getTo());
        adaptedRide.setDate(v.getDate());
        adaptedRide.setAmountOfSeats(v.getAmountOfSeats());
        adaptedRide.setPersons(ImmutableList.copyOf(v.getPersons()));
        return adaptedRide;
    }
}
