package repositories;

import entities.Person;
import entities.Result;
import entities.Ride;

import java.util.List;

/**
 * Created by domin_000 on 28.12.2015.
 */
public interface IPersonRepository extends IRepository {
    Result<Person> Login(String userName, String password);
    Result<Person> Register(String userName,String password, String confirmPassword);
    Result<Person> updateAccount(Person person);
    Result<Boolean> Logout(Person person);
}
