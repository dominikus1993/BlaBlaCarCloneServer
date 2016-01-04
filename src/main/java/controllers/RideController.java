package controllers;

import com.google.gson.Gson;
import entities.*;
import repositories.IAuthenticationRepository;
import repositories.IRidesRepository;
import spark.Request;
import spark.Response;
import utils.UserUtils;

/**
 * Created by dominik.kotecki on 04-01-2016.
 */
public class RideController extends BaseController{
    private final IRidesRepository ridesRepository;
    private final Gson gson;

    public RideController(IRidesRepository ridesRepository, IAuthenticationRepository authenticationRepository) {
        super(authenticationRepository);
        this.ridesRepository = ridesRepository;
        this.gson = new Gson();
    }

    public String get(Request request, Response response){
       return gson.toJson(ridesRepository.get());
    }

    public String create(Request request, Response response){
        try {
            Result<Person> authenticatedUser = getAuthenticationRepository().findByToken(request);
            if (authenticatedUser.isSuccess()) {
                return gson.toJson(new Result<Ride>(false, true, Result.CreateMessagesList("Unauthorized access")));
            }
            CreatedRide rideToCreate = gson.fromJson(request.body(), CreatedRide.class);
            return gson.toJson(ridesRepository.create(new Ride(Ride.getIdentityId(), authenticatedUser.getValue(), rideToCreate.getFrom(), rideToCreate.getTo(), rideToCreate.getPrice(), rideToCreate.getDate(), rideToCreate.getAmountOfSeats())));
        }catch (Exception ex){
            return gson.toJson(new Result<>());
        }

    }

    public String update(Request request, Response response){
        try {
            Result<Person> authenticatedUser = getAuthenticationRepository().findByToken(request);
            UpdateRide updateRide = gson.fromJson(request.body(), UpdateRide.class);
            Ride ride = ridesRepository.read(updateRide.getId());
            if (authenticatedUser.isSuccess() && ride.getOwner().getId() == authenticatedUser.getValue().getId()) {
                return gson.toJson(ridesRepository.update(updateRide));
            }
        }
        catch (Exception ex){
            return gson.toJson(new Result<>());
        }
        return gson.toJson(new Result<>());
    }
}
