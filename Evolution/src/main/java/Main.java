import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.*;
import com.mongodb.util.JSON;

import javax.jws.soap.SOAPBinding;
import javax.xml.crypto.Data;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Zeke on 18/03/2017.
 */
public class Main
{
    public static void main(String[] args)
    {
        try
        {
            DataBase evolution = new DataBase();
            evolution.Connect("evolution");
            //evolution.PrintAllDatabase();

            //TO FIXE: Recupere les nom des collections de merde
            System.out.println("Over Here ! : ");
            Set<String> s = evolution.dbEvolution.getCollectionNames();
            for (String a : s)
                System.out.println(a);


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
