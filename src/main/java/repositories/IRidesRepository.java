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
    Result<Ride> read(int id);
    Result<Boolean> delete(int id);
    Result<Ride> join(int id, int personId);
    Result<Ride> unJoin(int id, int personId);
}
