package entities;

import com.google.common.collect.ImmutableList;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dominik.kotecki on 28-12-2015.
 */
public class Person {
    private static AtomicInteger identity = new AtomicInteger(0);
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final ImmutableList<Ride> rides;

    public Person(int id, String firstName, String lastName, String email,String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.rides = ImmutableList.of();
    }

    public Person(int id, String firstName, String lastName, String email, String password, List<Ride> rides) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.rides = ImmutableList.copyOf(rides);
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public static int getIdentityId(){
        return identity.incrementAndGet();
    }

    public String getEmail() {
        return email;
    }


    public List<Ride> getRides() {
        return rides;
    }
}
