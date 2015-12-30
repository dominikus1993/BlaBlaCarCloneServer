package utils;

import entities.DataGenerator;
import repositories.IPersonRepository;
import repositories.PersonRepository;

/**
 * Created by domin_000 on 29.12.2015.
 */
public class SimpleDependencyResolver {

    private final static MyStaticDataBase dataBase = new MyStaticDataBase(DataGenerator.persons, DataGenerator.rides);

    public static MyStaticDataBase getMyStaticDataBase(){
        return dataBase;
    }

    public static IPersonRepository GetIPersonRepository(){
        return new PersonRepository(getMyStaticDataBase());
    }
}
