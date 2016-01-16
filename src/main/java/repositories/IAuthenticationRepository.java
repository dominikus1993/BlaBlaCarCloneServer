package repositories;

import entities.Person;
import entities.Result;
import spark.Request;

/**
 * Created by dominik.kotecki on 04-01-2016.
 */
public interface IAuthenticationRepository {
    Result<Person> findByToken(Request request);
    boolean delete(Request request);
    Result<String> create(Person person);
}
