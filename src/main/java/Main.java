import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Zeke on 20/05/2017.
 */
public class Main
{
    public static void main(String[] argv) throws Exception
    {

        //Statistic stat = new Statistic();
        //InterfaceGraphe ihm = new InterfaceGraphe();
        //ihm.launch();

        /*CallerApi s = new CallerApi();
        System.out.println("Testing - Send Http GET request");
        s.sendGet();*/



        GrapheChart.launch("ButtonTask");
    }


}
