package repositories;

import entities.Result;
import entities.Ride;
import entities.UpdateRide;
import utils.MyStaticDataBase;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    public Result<Ride> update(UpdateRide ride) {
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
    public Ride read(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Ride book(int id) {
        return null;
    }
}
