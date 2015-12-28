package entities;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dominik.kotecki on 28-12-2015.
 */
public class Person {
    public static AtomicInteger identity = new AtomicInteger(0);
    private final AtomicInteger id;
    private final String name;
    private final String password;

    public Person(AtomicInteger id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public AtomicInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
