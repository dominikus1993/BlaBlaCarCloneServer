/**
 * Created by domin_000 on 14.11.2015.
 */

import controllers.UserController;
import entities.DataGenerator;
import repositories.IPersonRepository;
import utils.MyStaticDataBase;
import utils.SetUpRoutes;
import utils.SimpleDependencyResolver;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        MyStaticDataBase dataBase = new MyStaticDataBase(DataGenerator.persons, DataGenerator.rides);
        IPersonRepository personRepository = SimpleDependencyResolver.GetIPersonRepository();
        UserController userController = new UserController(personRepository);

        SetUpRoutes routes = new SetUpRoutes(personRepository, userController);

        staticFileLocation("public");

        init();

        routes.setUp();



    }
}
