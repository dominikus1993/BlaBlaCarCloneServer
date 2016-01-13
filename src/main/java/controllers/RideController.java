package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.*;
import repositories.IAuthenticationRepository;
import repositories.IRidesRepository;
import spark.Request;
import spark.Response;

/**
 * Created by dominik.kotecki on 04-01-2016.
 */
public class RideController extends BaseController{
    private final IRidesRepository ridesRepository;
    private final Gson gson;

    public RideController(IRidesRepository ridesRepository, IAuthenticationRepository authenticationRepository) {
        super(authenticationRepository);
        this.ridesRepository = ridesRepository;
        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        this.gson = builder.create();
    }

    public String get(Request request, Response response){
       return gson.toJson(ridesRepository.get());
    }

    public String getById(Request request, Response response){
        try {
            return gson.toJson(ridesRepository.read(Integer.parseInt(request.params(":id"))));
        }catch (Exception ex){
            response.status(404);
            return gson.toJson(Result.Error("Bad Request"));
        }
    }

    public String create(Request request, Response response){
        try {
            Result<Person> authenticatedUser = getAuthenticationRepository().findByToken(request);
            if (!isAuthenticated(request)) {
                return gson.toJson(new Result<Ride>(false, true, Result.CreateMessagesList("Unauthorized access")));
            }
            CreatedRide rideToCreate = gson.fromJson(request.body(), CreatedRide.class);
            return gson.toJson(ridesRepository.create(new Ride(Ride.getIdentityId(), authenticatedUser.getValue(), rideToCreate.getFrom(), rideToCreate.getTo(), rideToCreate.getPrice(), rideToCreate.getDate(), rideToCreate.getAmountOfSeats())));
        }catch (Exception ex){
            response.status(404);
            return gson.toJson(Result.Error());
        }

    }

    public String update(Request request, Response response){
        try {
            Result<Person> authenticatedUser = getAuthenticationRepository().findByToken(request);
            UpdateRide updateRide = gson.fromJson(request.body(), UpdateRide.class);
            Result<Ride> ride = ridesRepository.read(updateRide.getId());
            if (isOwner(authenticatedUser, ride)) {
                return gson.toJson(ridesRepository.update(updateRide));
            }
        }
        catch (Exception ex){
            response.status(404);
            return gson.toJson(Result.Error());
        }
        response.status(404);
        return gson.toJson(Result.Error());
    }

    public String delete(Request request, Response response)
    {
        try {
            int id = Integer.parseInt(request.params(":id"));
            Result<Person> userResult = getAuthenticationRepository().findByToken(request);
            Result<Ride> rideResult = ridesRepository.read(id);
            if (isOwner(userResult, rideResult)){
                return gson.toJson(ridesRepository.delete(id));
            }

        }catch (Exception ex){
            return gson.toJson(Result.Error());
        }
        return gson.toJson(Result.Error());
    }

    public String join(Request request, Response response){
        try{
            Result<Person> authenticatedUser = getAuthenticationRepository().findByToken(request);
            if (authenticatedUser.isSuccess()){
                final int rideId = Integer.parseInt(request.params(":id"));
                final int personId = authenticatedUser.getValue().getId();
                response.status(200);
                return gson.toJson(ridesRepository.join(rideId,personId));
            }else{
                response.status(404);
                return gson.toJson(Result.Error("Unauthorized access"));
            }
        }catch(Exception ex){
            response.status(404);
            return gson.toJson(Result.Error("Bad Request"));
        }

    }

    private boolean isAuthenticated(Request request){
        Result<Person> authenticatedUser = getAuthenticationRepository().findByToken(request);
        return authenticatedUser.isSuccess();
    }

    private boolean isOwner(Result<Person> userResult, Result<Ride> rideResult) {
        return userResult.isSuccess() && rideResult.isSuccess() && rideResult.getValue().getOwner().getId() == userResult.getValue().getId();
    }

}
