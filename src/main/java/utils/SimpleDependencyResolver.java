package utils;

import entities.DataGenerator;
import repositories.IPersonRepository;
import repositories.IRidesRepository;
import repositories.PersonRepository;
import repositories.RidesRepository;

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

    public static IRidesRepository getIRidesRepository(){
        return new RidesRepository(getMyStaticDataBase());
    }
}
