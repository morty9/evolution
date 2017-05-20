/**
 * Created by Zeke on 20/05/2017.
 */
public class Main
{
    public static void main(String[] argv) throws Exception
    {
        Statistic stat = new Statistic();

        System.out.println("Testing - Send Http GET request");
        stat.sendGet();

        System.out.println("Testing - Send Http POST request");
        //URL not found
        //stat.setUrl("https://selfsolve.apple.com/wcResults.do");
        //stat.sendPost();
    }
}
