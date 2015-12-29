package utils;

import repositories.IPersonRepository;
import repositories.PersonRepository;

/**
 * Created by domin_000 on 29.12.2015.
 */
public class SimpleDependencyResolver {
    public static IPersonRepository GetIPersonRepository(){
        return new PersonRepository();
    }
}
