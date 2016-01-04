package repositories;

import entities.Result;
import entities.Ride;
import entities.UpdateRide;

import java.util.List;

/**
 * Created by domin_000 on 28.12.2015.
 */
public interface IRidesRepository extends IRepository {
    Result<List<Ride>> get();
    Result<Ride> create(Ride ride);
    Result<Ride> update(UpdateRide ride);
    Ride read(int id);
    boolean delete(int id);
    Ride book(int id);
}
