import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Created by Zeke on 27/03/2017.
 */
public class Project
{
    public String name;
    public List<Sprint> listSprint;

    public Project(String name)
    {
        this.name = name;
    }

    public void AddSprint(Sprint sprint)
    {
        this.listSprint.add(sprint);
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
