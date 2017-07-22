import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by Zeke on 06/07/2017.
 */
public class CallerApi
{
    private final String NET_AGENT = "Mozilla/5.0";
    private String strUrl = "http://services.groupkt.com/state/get/IND/all";

    public CallerApi()
    { }

    public void jsonReader(BufferedReader reader)
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            Map<String, Object> map = mapper.readValue(reader, new TypeReference<Map<String, String>>() {
            });
            System.out.println(map);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void sendGet() throws Exception
    {
        URL url = new URL(strUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("NET_AGENT", NET_AGENT);
        int res = conn.getResponseCode();

        BufferedReader buffInput = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        jsonReader(buffInput);

        /*String outPut;
        StringBuffer response = new StringBuffer();

        while ((outPut = buffInput.readLine()) != null)
        {
            response.append(outPut);
        }*/


        //System.out.println("JSON: " + response.toString());
        buffInput.close();
    }
}
