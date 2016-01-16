package repositories;

import entities.Person;
import entities.Result;
import spark.Request;
import utils.MyStaticDataBase;
import utils.UserUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by dominik.kotecki on 04-01-2016.
 */
public class AuthenticationRepository extends BaseRepository implements IAuthenticationRepository {

    public AuthenticationRepository(MyStaticDataBase dataBase) {
        super(dataBase);
    }

    @Override
    public Result<Person> findByToken(Request request) {
        String token = request.headers("Authorization");
        return new Result<>(getDataBase().getAuthTokens().get(token));
    }

    @Override
    public boolean delete(Request request){
        String token = request.headers("Authorization");
        getDataBase().setAuthTokens((HashMap<String, Person>) getDataBase().getAuthTokens().entrySet()
                .parallelStream()
                .filter(e -> !e.getKey().equals(token))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        return true;
    }

    @Override
    public Result<String> create(Person person) {
        String token = UserUtils.GenerateAuthenticationToken(person.getEmail());
        getDataBase().getAuthTokens().put(token, person);
        Person addedPerson = getDataBase().getAuthTokens().put(token, person);
        return addedPerson == null ? new Result<>() : new Result<>(token);
    }

}
