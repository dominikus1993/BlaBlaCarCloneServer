package utils;
import entities.Person;
import entities.Ride;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by domin_000 on 29.12.2015.
 */
@XmlRootElement(name = "database")
public class MyStaticDataBase {

    private List<Person> persons;
    private List<Ride> rides;
    private HashMap<String,Person> authTokens;

    public MyStaticDataBase() {
    }

    public MyStaticDataBase(List<Person> persons, List<Ride> rides){
        this.persons = persons;
        this.rides = rides;
        this.authTokens = new HashMap<>();
        XmlDataManager.saveChanges(this);
    }

    @XmlElement
    public List<Person> getPersons() {
        return persons;
    }

    @XmlElement
    public List<Ride>  getRides() {
        return rides;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    @XmlTransient
    public HashMap<String, Person> getAuthTokens() {
        return authTokens;
    }

    public void setAuthTokens(HashMap<String, Person> authTokens) {
        this.authTokens = authTokens;
    }
}
