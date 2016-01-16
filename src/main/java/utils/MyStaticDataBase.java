package utils;
import entities.Person;
import entities.Ride;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by domin_000 on 29.12.2015.
 */
public class MyStaticDataBase {
    private List<Person> persons;
    private List<Ride> rides;
    private HashMap<String,Person> authTokens;


    public MyStaticDataBase(List<Person> persons, List<Ride> rides) throws SQLException {
        this.persons = persons;
        this.rides = rides;
        this.authTokens = new HashMap<>();
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

    public HashMap<String, Person> getAuthTokens() {
        return authTokens;
    }

    public int saveChanges(){
        return 1;
    }
}
