package entities;

import com.google.common.collect.ImmutableList;
import com.google.gson.annotations.Expose;

/**
 * Created by domin_000 on 16.01.2016.
 */
public class AdaptedPerson {

    private  int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private ImmutableList<Ride> rides;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ImmutableList<Ride> getRides() {
        return rides;
    }

    public void setRides(ImmutableList<Ride> rides) {
        this.rides = rides;
    }
}
