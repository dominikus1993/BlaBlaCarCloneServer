package utils;

import controllers.RideController;
import controllers.UserController;
import entities.Person;
import repositories.IPersonRepository;
import spark.Request;

import static spark.Spark.*;

/**
 * Created by domin_000 on 03.01.2016.
 */
public class SetUpRoutes {
    private final UserController userController;
    private final RideController rideController;

    public SetUpRoutes(UserController userController, RideController rideController) {
        this.userController = userController;
        this.rideController = rideController;
    }

    public void setUp(){
        //User
        get("/user/login/:login/password/:password", (userController::Login));
        post("/user/register", userController::Register);


        //Rides
        get("/rides/all", rideController::get);
        get("/rides/:id", rideController::getById);
        post("/rides/create", rideController::create);
        put("/rides/update", (rideController::update));
        delete("/rides/delete/:id", rideController::delete);
        get("/rides/ride/:id/person/:personId", rideController::join);
    }



}
