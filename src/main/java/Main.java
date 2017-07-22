import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Zeke on 20/05/2017.
 */
public class Main
{
    public static void main(String[] argv) throws Exception
    {


        /*CallerApi s = new CallerApi();
        System.out.println("Testing - Send Http GET request");
        s.sendGet();*/

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
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

        double[] res = AlgorithmGraphe.getDatasetTask(l);

        Date date = new Date();
        String str = AlgorithmGraphe.dateToDayName(date);
        System.out.println(str);

        //GrapheChart.launch("ButtonTask");
    }


}
