package utils;

import com.google.common.collect.ImmutableList;
import entities.AdaptedPerson;
import entities.Person;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by domin_000 on 16.01.2016.
 */
public class PersonAdapter extends XmlAdapter<AdaptedPerson, Person> {

    @Override
    public Person unmarshal(AdaptedPerson v) throws Exception {
       return new Person(v.getFirstName(), v.getLastName(), v.getEmail(), v.getPassword(), v.getRides());
    }

    @Override
    public AdaptedPerson marshal(Person v) throws Exception {
            AdaptedPerson adaptedPerson = new AdaptedPerson();
            adaptedPerson.setId(v.getId());
            adaptedPerson.setFirstName(v.getFirstName());
            adaptedPerson.setLastName(v.getLastName());
            adaptedPerson.setEmail(v.getEmail());
            adaptedPerson.setPassword(v.getPassword());
            adaptedPerson.setRides(ImmutableList.copyOf(v.getRides()));
            return adaptedPerson;
    }
}
