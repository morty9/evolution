import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Zeke on 06/07/2017.
 */
public class CallerApi
{
    private final String NET_AGENT = "Mozilla/5.0";

    public CallerApi()
    { }

    /**
     * \fn String sendGet(String strUrl) throws Exception
     * \brief Send a get request and return the Json string
     * \details Create httpconnection with an url and put the result in a String
     *
     * \param strUrl Request url in string type
     * \return the Json string
     */
    public String sendGet(String strUrl) throws Exception {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("NET_AGENT", NET_AGENT);
            int resCode = conn.getResponseCode();

            BufferedReader buffInput = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            //Get the Json string
            String outPut;
            StringBuffer response = new StringBuffer();
            while ((outPut = buffInput.readLine()) != null) {
                response.append(outPut);
            }
            String jsonString = response.toString();

            buffInput.close();
            return jsonString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * \fn List<Stat> getListStatFromJson(String jsonString)
     * \brief Get the list of stat from the Json string
     * \details Transform the json string into list of object stat with an objectmapper
     *
     * \param jsonString the Json in string type
     * \return the list of all stat in the database
     */
    public List<Stat> getListStatFromJson(String jsonString) {
        //Transform the Json to string
        ObjectMapper mapper = new ObjectMapper();

        TypeReference<List<Stat>> mapperType = new TypeReference<List<Stat>>() {};
        try {
            List<Stat> listProject = mapper.readValue(jsonString, mapperType);
            return listProject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * \fn Project getProjectFromJson(String jsonString)
     * \brief Get the Project from the Json string
     * \details Transform the json string into list of object project with an objectmapper
     *
     * \param jsonString the Json in string type
     * \return the object project in the database
     */
    public Project getProjectFromJson(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Project> mapperType = new TypeReference<Project>() {};
        try {
            Project project = mapper.readValue(jsonString, mapperType);
            return project;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * \fn Sprint getSprintFromJson(String jsonString)
     * \brief Get the Sprint from the Json string
     * \details Transform the json string into list of object sprint with an objectmapper
     *
     * \param jsonString the Json in string type
     * \return the object sprint in the database
     */
    public Sprint getSprintFromJson(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Sprint> mapperType = new TypeReference<Sprint>() {};
        try {
            Sprint sprint = mapper.readValue(jsonString, mapperType);
            return sprint;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * \fn Task getTaskFromJson(String jsonString)
     * \brief Get the Task from the Json string
     * \details Transform the json string into list of object task with an objectmapper
     *
     * \param jsonString the Json in string type
     * \return the object task in the database
     */
    public Task getTaskFromJson(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Task> mapperType = new TypeReference<Task>() {};
        try {
            Task task = mapper.readValue(jsonString, mapperType);
            return task;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
