package utils;

import entities.DataGenerator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;

/**
 * Created by domin_000 on 16.01.2016.
 */
public class XmlDataManager {

    public final static String DATABASE_FILE_PATH = "database.xml";

    private static File getDatabaseFile(){
        return new File(DATABASE_FILE_PATH);
    }

    public static MyStaticDataBase loadChanges(){
        try {
            JAXBContext context = JAXBContext.newInstance(MyStaticDataBase.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MyStaticDataBase dataBase = (MyStaticDataBase) unmarshaller.unmarshal(getDatabaseFile());
            if(dataBase.getAuthTokens() == null){
                dataBase.setAuthTokens(new HashMap<>());
            }
            return dataBase;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new MyStaticDataBase(DataGenerator.persons, DataGenerator.rides);
    }

    public static boolean saveChanges(MyStaticDataBase dataBase){
        try {
            JAXBContext context = JAXBContext.newInstance(MyStaticDataBase.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(dataBase, getDatabaseFile());
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
            return false;
        }
    }
}
