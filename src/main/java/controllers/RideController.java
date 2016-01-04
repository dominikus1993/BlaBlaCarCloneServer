package controllers;

import com.google.gson.Gson;
import entities.CreatedRide;
import entities.Person;
import entities.Result;
import entities.Ride;
import repositories.IRidesRepository;
import spark.Request;
import spark.Response;
import utils.UserUtils;

/**
 * Created by dominik.kotecki on 04-01-2016.
 */
public class RideController {
    private final IRidesRepository ridesRepository;
    private final Gson gson;

    public RideController(IRidesRepository ridesRepository) {
        this.ridesRepository = ridesRepository;
        this.gson = new Gson();
    }

    public String get(Request request, Response response){
       return gson.toJson(ridesRepository.get());
    }

    public String create(Request request, Response response){
        Person authenticatedUser = UserUtils.getAuthenticatedUser(request);
        if (authenticatedUser == null){
            return gson.toJson(new Result<Ride>(false,true, Result.CreateMessagesList("Unauthorized access")));
        }
        CreatedRide rideToCreate = gson.fromJson(request.body(), CreatedRide.class);
        return gson.toJson(ridesRepository.create(new Ride(Ride.getIdentityId(),authenticatedUser, rideToCreate.getFrom(),rideToCreate.getTo(), rideToCreate.getPrice(), rideToCreate.getDate(), rideToCreate.getAmountOfSeats())));
    }

    public String update(Request request, Response response){
        Person authenticatedUser = UserUtils.getAuthenticatedUser(request);
        if (authenticatedUser == null){
            return "";
        }
        return "";
    }
}
