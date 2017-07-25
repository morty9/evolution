import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Zeke on 20/05/2017.
 */
public class Main
{
    public static void main(String[] argv) throws Exception
    {

        /*CallerApi call = new CallerApi();
        System.out.println("Testing - Send Http GET request");

        String jsonStat = call.sendGet("http://127.0.0.1:3000/scrummary/stats");
        java.util.List<Stat> l = call.getListStatFromJson(jsonStat);

        int idProj = l.get(0).getId_project();

        String jsonProject = call.sendGet("http://127.0.0.1:3000/scrummary/projects/" + idProj);
        Project p = call.getProjectFromJson(jsonProject);

        int[] listIdSprint = p.getId_sprint();
        int idSprint = listIdSprint[0];

        String jsonSprint = call.sendGet("http://127.0.0.1:3000/scrummary/sprints/" + idSprint);
        Sprint s = call.getSprintFromJson(jsonSprint);

        String jsonTask = call.sendGet("http://127.0.0.1:3000/scrummary/tasks/2");
        Task t = call.getTaskFromJson(jsonTask);*/


        /*SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String d1 = "18/07/2017";
        String d2 = "19/07/2017";
        Date date1 = format.parse(d1);
        Date date2 = format.parse(d2);

        models.Task t1 = new models.Task();
        models.Task t2 = new models.Task();
        models.Task t3 = new models.Task(date1);
        models.Task t4 = new models.Task(date2);
        models.Task t5 = new models.Task();
        models.Task t6 = new models.Task(date1);
        ArrayList<models.Task> l = new ArrayList<>();
        l.add(t1);
        l.add(t2);
        l.add(t3);
        l.add(t4);
        l.add(t5);
        l.add(t6);
        double[] res = AlgorithmGraph.getDatasetTask(l);*/

        /*Date date = new Date();
        String str = AlgorithmGraph.dateToDayName(date);
        System.out.println(str);*/

        //GrapheChart.launch("ButtonTask");

        String d1 = "2017/07/22";
        String d2 = "2017/07/30";

        ArrayList<Date> listDate = AlgorithmGraph.getListDate(d1, d2);
    }


}
