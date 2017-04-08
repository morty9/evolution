import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.impl.orbutil.ObjectWriter;

/**
 * Created by Zeke on 27/03/2017.
 */
public class User
{
    //public int id;
    public String name;

    public User()
    {
        //this.id = 0;
        this.name = "";
    }

    public User(String name)
    {
        //this.id = 0;
        this.name = name;
    }

    public String GetJson()
    {
        String json = null;
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
