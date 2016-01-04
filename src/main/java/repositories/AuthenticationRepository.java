package repositories;

import entities.Person;
import entities.Result;
import spark.Request;
import utils.MyStaticDataBase;
import utils.UserUtils;

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
    public Result<String> create(Person person) {
        String token = UserUtils.GenerateAuthenticationToken();
        getDataBase().getAuthTokens().put(token, person);
        Person addedPerson = getDataBase().getAuthTokens().put(token, person);
        return addedPerson == null ? new Result<>() : new Result<>(token);
    }

}
