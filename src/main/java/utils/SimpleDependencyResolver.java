package utils;

import entities.DataGenerator;
import repositories.*;

/**
 * Created by domin_000 on 29.12.2015.
 */
public class SimpleDependencyResolver {

    private final static MyStaticDataBase dataBase = XmlDataManager.loadChanges();

    public static MyStaticDataBase getMyStaticDataBase(){
        return dataBase;
    }

    public static IPersonRepository GetIPersonRepository(){
        return new PersonRepository(getMyStaticDataBase());
    }

    public static IRidesRepository getIRidesRepository(){
        return new RidesRepository(getMyStaticDataBase());
    }

    public static IAuthenticationRepository getIAuthenticationRepository(){
        return new AuthenticationRepository(getMyStaticDataBase());
    }
}
