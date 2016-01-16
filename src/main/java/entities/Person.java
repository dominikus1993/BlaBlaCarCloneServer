package entities;

import com.google.common.collect.ImmutableList;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dominik.kotecki on 28-12-2015.
 */
public class Person implements Serializable {
    private static AtomicInteger identity = new AtomicInteger(0);

    @Expose
    private final int id;

    @Expose
    private final String firstName;

    @Expose
    private final String lastName;

    @Expose
    private final String email;

    private final String password;

    @Expose
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

    public Person(String firstName, String lastName, String email, String password, List<Ride> rides) {
        this.id = identity.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.rides = ImmutableList.copyOf(rides);
    }

    public Person(String firstName, String lastName, String email, String password) {
        this.id = identity.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.rides = ImmutableList.of();
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

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (password != null ? !password.equals(person.password) : person.password != null) return false;
        return rides != null ? rides.equals(person.rides) : person.rides == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (rides != null ? rides.hashCode() : 0);
        return result;
    }
}
