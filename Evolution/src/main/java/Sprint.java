import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Created by Zeke on 27/03/2017.
 */
public class Sprint
{
    public int time;
    public List<Task> listTask;

    public Sprint(int time)
    {
        this.time = time;
    }

    public void AddTask(Task task)
    {
        this.listTask.add(task);
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
