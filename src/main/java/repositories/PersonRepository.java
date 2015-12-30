package repositories;

import entities.Person;
import utils.MyStaticDataBase;

import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by domin_000 on 29.12.2015.
 */
public class PersonRepository extends BaseRepository implements IPersonRepository {

    public PersonRepository(MyStaticDataBase dataBase) {
        super(dataBase);
    }

    @Override
    public Person Login(String userName, String password) {
        try{
            return getDataBase().getPersons().parallelStream().filter(person -> Objects.equals(person.getEmail(), userName) && person.getPassword().equals(password)).findAny().get();
        }catch (Exception ex){
            return null;
        }

    }

    @Override
    public Person Register(String userName, String password, String confirmPassword) {
        if (password.equals(confirmPassword) && getDataBase().getPersons().parallelStream().filter(person -> person.getEmail().equals(userName)).toArray().length == 0){
            Person personToAdd = new Person(Person.getIdentityId(), null, null, userName , password);
            getDataBase().getPersons().add(personToAdd);
            return personToAdd;
        }
        return null;
    }

    @Override
    public Person updateAccount(Person person) {
        getDataBase().setPersons(getDataBase().getPersons().stream().map(person1 -> {
            if (person.getId() == person.getId()){
                return new Person(person.getId(), person.getFirstName(), person.getLastName(), person.getEmail(), person.getPassword(), person.getRides());
            }
            else{
                return person1;
            }
        }).collect(Collectors.toList()));
        return person;
    }

    @Override
    public boolean Logout(Person person) {
        return false;
    }
}
