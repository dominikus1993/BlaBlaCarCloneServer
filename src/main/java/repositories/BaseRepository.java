package repositories;

import utils.MyStaticDataBase;

/**
 * Created by domin_000 on 29.12.2015.
 */
public abstract class BaseRepository implements IRepository{
    private MyStaticDataBase dataBase;

    public BaseRepository(MyStaticDataBase dataBase) {
        this.dataBase = dataBase;
    }

    public MyStaticDataBase getDataBase() {
        return dataBase;
    }
}
