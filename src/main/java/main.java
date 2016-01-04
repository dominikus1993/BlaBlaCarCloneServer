/**
 * Created by domin_000 on 14.11.2015.
 */

import controllers.RideController;
import controllers.UserController;
import entities.DataGenerator;
import repositories.IAuthenticationRepository;
import repositories.IPersonRepository;
import repositories.IRidesRepository;
import utils.MyStaticDataBase;
import utils.SetUpRoutes;
import utils.SimpleDependencyResolver;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        IPersonRepository personRepository = SimpleDependencyResolver.GetIPersonRepository();
        IRidesRepository ridesRepository = SimpleDependencyResolver.getIRidesRepository();
        IAuthenticationRepository authenticationRepository = SimpleDependencyResolver.getIAuthenticationRepository();

        UserController userController = new UserController(personRepository,authenticationRepository);
        RideController rideController = new RideController(ridesRepository, authenticationRepository);

        SetUpRoutes routes = new SetUpRoutes(userController, rideController);

        staticFileLocation("public");

        init();

        routes.setUp();



    }
}
