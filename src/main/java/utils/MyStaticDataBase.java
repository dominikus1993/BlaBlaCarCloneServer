package utils;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.google.common.collect.ImmutableList;
import entities.Person;
import entities.Ride;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by domin_000 on 29.12.2015.
 */
public class MyStaticDataBase {
    private static final String DATABASE_NAME = "blablacar";
    private static final String HOST = "localhost";
    private static final int PORT = 27017;
    private List<Person> persons;
    private List<Ride> rides;
    private HashMap<String,Person> authTokens;

    private final MongoClient client;
    private final Datastore datastore;
    private final Morphia morphia;

    public MyStaticDataBase(List<Person> persons, List<Ride> rides) {
        morphia = new Morphia();
        client = new MongoClient(HOST, PORT);
        datastore = morphia.createDatastore(client, DATABASE_NAME);

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
