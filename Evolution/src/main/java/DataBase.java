import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.*;
import com.mongodb.util.JSON;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

/**
 * Created by Zeke on 27/03/2017.
 */
public class DataBase
{
    public Mongo mongoEvolution;
    public DB dbEvolution;

    public void InitStrucDataBase()
    {
        CreateTable("User");
        CreateTable("Project");
        CreateTable("Sprint");
        CreateTable("Task");
    }

    public void Connect(String dbName)
    {
        try
        {
            mongoEvolution = new Mongo("localhost", 27017);
            if (checkDBExist(dbName))
            {
                dbEvolution = mongoEvolution.getDB(dbName);
                System.out.println("Connection to the Database " + dbName + " successfully !");
            }
            else
                System.out.println("Database " + dbName + "was not found. Connection impossible !");
        } catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
    }

    public boolean checkDBExist(String name)
    {
        List<String> listDB = mongoEvolution.getDatabaseNames();
        for (String nameDB : listDB)
        {
            if (nameDB.equals(name))
                return true;
        }
        return false;
    }

    public void AddDataInTable(String name, String jsonObject)
    {
        try
        {
            DBCollection dbCollection = dbEvolution.getCollection(name);
            DBObject dbObject = (DBObject)JSON.parse(jsonObject);
            dbCollection.insert(dbObject);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void CreateTable(String name)
    {
        try
        {
            DBCollection dbcollection = dbEvolution.createCollection(name, new BasicDBObject());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean checkCollectionExist(String name)
    {
        Set<String> listCollection = dbEvolution.getCollectionNames();
        for (String str : listCollection)
        {
            if (str.equals(name))
            {
                return true;
            }
        }
        return false;
    }

    public void PrintAllDatabase()
    {
        try
        {
            List<String> listName = mongoEvolution.getDatabaseNames();
            System.out.println("All Database Name :");
            for (String s : listName)
            {
                System.out.println(s);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
