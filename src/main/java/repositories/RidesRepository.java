package repositories;

import entities.Ride;
import utils.MyStaticDataBase;

import java.util.Collection;
import java.util.List;

/**
 * Created by domin_000 on 30.12.2015.
 */
public class RidesRepository extends BaseRepository implements IRidesRepository {

    public RidesRepository(MyStaticDataBase dataBase) {
        super(dataBase);
    }

    @Override
    public synchronized Ride create(Ride ride) {
        getDataBase().getRides().add(ride);
        return ride;
    }

    @Override
    public Ride update(Ride ride) {
        return null;
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
