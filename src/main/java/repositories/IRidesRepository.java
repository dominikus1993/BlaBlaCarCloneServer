package repositories;

import entities.Ride;

/**
 * Created by domin_000 on 28.12.2015.
 */
public interface IRidesRepository {
    Ride create(Ride ride);
    Ride update(Ride ride);
    Ride read(int id);
    boolean delete(int id);
    Ride book(int id);
}
