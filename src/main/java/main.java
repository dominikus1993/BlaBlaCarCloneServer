/**
 * Created by domin_000 on 14.11.2015.
 */
import entities.DataGenerator;
import utils.MyStaticDataBase;

import java.util.List;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        MyStaticDataBase dataBase = new MyStaticDataBase(DataGenerator.persons, DataGenerator.rides);
        dataBase.getPersons();
        staticFileLocation("public");
        webSocket("/chat", WebSocketHandler.class);
        init();
    }
}
