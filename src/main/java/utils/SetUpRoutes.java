package utils;

import controllers.UserController;
import repositories.IPersonRepository;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by domin_000 on 03.01.2016.
 */
public class SetUpRoutes {
    private final IPersonRepository personRepository;
    private final UserController userController;

    public SetUpRoutes(IPersonRepository personRepository, UserController userController) {
        this.personRepository = personRepository;
        this.userController = userController;
    }

    public void setUp(){

        get("/user/login/:login/password/:password", (userController::Login));

        post("user/register", userController::Register);
    }


}
