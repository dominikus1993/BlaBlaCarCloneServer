package repositories;

import entities.Person;
import entities.Result;
import entities.Ride;
import entities.UpdateRide;
import utils.MyStaticDataBase;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by domin_000 on 30.12.2015.
 */
public class RidesRepository extends BaseRepository implements IRidesRepository {

    public RidesRepository(MyStaticDataBase dataBase) {
        super(dataBase);
    }

    @Override
    public Result<List<Ride>> get() {
        return new Result<>(getDataBase().getRides());
    }

    @Override
    public synchronized Result<Ride> create(Ride ride) {
        getDataBase().getRides().add(ride);
        return new Result<>(ride);
    }

    @Override
    public synchronized Result<Ride> update(UpdateRide ride) {
        getDataBase().setRides(getDataBase().getRides().stream().map(ride1 -> {
            if (ride1.getId() == ride.getId()){
                return new Ride(ride1.getId(), ride1.getOwner(), ride.getFrom(), ride.getTo(), ride.getPrice(), ride.getDate(), ride.getAmountOfSeats(),ride1.getPersons());
            }
            return ride1;
        }).collect(Collectors.toList()));

        try{
            return new Result<>(getDataBase().getRides().parallelStream().filter(x -> x.getId() == ride.getId()).findAny().get());
        }catch (Exception ex){
            return new Result<>();
        }
    }

    @Override
    public Result<Ride> read(int id) {
        try {
            return new Result<>(getDataBase().getRides().parallelStream().filter(ride -> ride.getId() == id).findAny().get());
        }catch (Exception ex){
            return new Result<>();
        }
    }

    @Override
    public Result<Boolean> delete(int id) {
        try{
            Ride rideToRemove = getDataBase().getRides().parallelStream().filter(x -> x.getId() == id).findAny().get();
            return new Result<>(getDataBase().getRides().remove(rideToRemove));
        }catch(Exception ex){
            return new Result<>();
        }
    }

    @Override
    public Result<Ride> join(int id, int personId) {
        try{
            Ride ride = getDataBase().getRides().parallelStream().filter(x -> x.getId() == id).findAny().get();
            boolean isPassenger = !ride.getPersons().parallelStream().noneMatch(x -> x.getId() == personId);
            if (!isPassenger && ride.getPersons().size() <= ride.getAmountOfSeats()){
                getDataBase().setRides(getDataBase().getRides().stream().map(ride1 -> {
                    if (ride1.getId() == id){
                        List<Person> persons = new LinkedList<>(Arrays.asList(getDataBase().getPersons().parallelStream().filter(x -> x.getId() == personId).findAny().get()));
                        return new Ride(ride1.getId(),ride1.getOwner(),ride1.getFrom(), ride1.getTo(), ride1.getAmountOfSeats(), ride1.getDate(), ride1.getAmountOfSeats(),Stream.concat(ride1.getPersons().stream(), persons.stream()).collect(Collectors.toList()));
                    }
                    else {
                        return ride1;
                    }
                }).collect(Collectors.toList()));
                return new Result<>(getDataBase().getRides().parallelStream().filter(x -> x.getId() == id).findAny().get());
            }
        }catch (Exception ex){
            return Result.Error("Error");
        }
        return Result.Error("Error");
    }

    @Override
    public Result<Ride> unJoin(int id, int personId) {
        return null;
    }

}
