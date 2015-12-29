package repositories;

import entities.Person;
import utils.MyStaticDataBase;

import java.util.Objects;

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
        return null;
    }

    @Override
    public boolean Logout(Person person) {
        return false;
    }
}
