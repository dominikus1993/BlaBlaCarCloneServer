package repositories;

import entities.Person;
import entities.Ride;

import java.util.List;

/**
 * Created by domin_000 on 28.12.2015.
 */
public interface IPersonRepository extends IRepository {
    Person Login(String userName, String password);
    Person Register(String userName,String password, String confirmPassword);
    Person updateAccount(Person person);
    boolean Logout(Person person);
}
