import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import models.DataGraph;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Zeke on 14/07/2017.
 */
public class GrapheChart extends ApplicationFrame
{

    public GrapheChart(String title)
    {
        super(title);
    }

    /**
     * \fn JFreeChart createTaskChart(final CategoryDataset dataset)
     * \brief create the task chart
     * \details build a chart of the current project and the current sprint
     *
     * \param dataset the datagraph object
     * \return the Jfreechart object
     */
    public JFreeChart createTaskChart(final CategoryDataset dataset)
    {
        final JFreeChart chart = ChartFactory.createAreaChart(
                "Graphe des Tâches",
                "Jours du sprint",
                "Heure total des tâches",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setForegroundAlpha(0.5f);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        //plot.setOutlinePaint();

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLabelAngle(0 * Math.PI / 2.0);
        rangeAxis.setAutoRangeIncludesZero(false);


        return chart;
    }

    /**
     * \fn JFreeChart createBusinessChart(final CategoryDataset dataset)
     * \brief create the business chart
     * \details build a chart of the current project and the current sprint
     *
     * \param dataset the datagraph object
     * \return the Jfreechart object
     */
    public JFreeChart createBusinessChart(final CategoryDataset dataset)
    {
        final JFreeChart chart = ChartFactory.createAreaChart(
                "Graphe du Business",
                "Jours du sprint",
                "Coût",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chart.setBackgroundPaint(Color.white);
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setForegroundAlpha(0.5f);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLabelAngle(0 * Math.PI / 2.0);
        rangeAxis.setAutoRangeIncludesZero(false);

        return chart;
    }


    /**
     * \fn JFreeChart launchTask(DataGraph d)
     * \brief launch the task chart
     * \details Set a windows which contain the task chart
     *
     * \param d the datagraph object
     * \return the chart of the task graph
     */
    public static JFreeChart launchTask(DataGraph d)
    {
        JDialog graphWindows = new JDialog();
        Dimension dimensionWindow = Toolkit.getDefaultToolkit().getScreenSize();
        graphWindows.setLocation((int)((dimensionWindow.getWidth() / 2) - (graphWindows.getWidth() / 2)),
                (int)((dimensionWindow.getHeight() / 2) - (graphWindows.getHeight() / 2)));
        graphWindows.setTitle("Graphique des tâches");
        double[] dataGraph = AlgorithmGraph.getDatasetTask(d);

        final double[][] data = new double[][] {
                AlgorithmGraph.createOptimalData(AlgorithmGraph.maxTaskHours(d.getListTask())),
                dataGraph
        };

        String[] rowGraphe = {"Progression Optimale", "Graphe des tâches"};
        String[] columnsGraphe = d.getListDateName();

        GrapheChart graphe = new GrapheChart("Graphe des Tâches");
        final JFreeChart chart;
        final CategoryDataset dataset;

        dataset = DatasetUtilities.createCategoryDataset(rowGraphe, columnsGraphe, data);
        chart = graphe.createTaskChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setEnforceFileExtensions(false);

        graphWindows.getContentPane().add(chartPanel);
        graphWindows.pack();
        graphWindows.setVisible(true);

        return chart;
    }

    /**
     * \fn JFreeChart launchBusiness(DataGraph d)
     * \brief launch the business chart
     * \details Set a windows which contain the business chart
     *
     * \param d the datagraph object
     * \return the chart of the business graph
     */
    public static JFreeChart launchBusiness(DataGraph d)
    {
        JDialog graphWindows = new JDialog();
        Dimension dimensionWindow = Toolkit.getDefaultToolkit().getScreenSize();
        graphWindows.setLocation((int)((dimensionWindow.getWidth() / 2) - (graphWindows.getWidth() / 2)),
                (int)((dimensionWindow.getHeight() / 2) - (graphWindows.getHeight() / 2)));
        graphWindows.setTitle("Graphique Business");
        double[] dataBusinessGraph = AlgorithmGraph.getDatasetBusiness(d);

        final double[][] dataB = new double[][] {
                dataBusinessGraph
        };

        String[] columnsGraphe = d.getListDateName();
        String[] rowGrapheB = {"Graphe Business"};

        GrapheChart graphe = new GrapheChart("Graphe Business");
        final JFreeChart chart;
        final CategoryDataset dataset;

        dataset = DatasetUtilities.createCategoryDataset(rowGrapheB, columnsGraphe, dataB);
        chart = graphe.createBusinessChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setEnforceFileExtensions(false);

        graphWindows.getContentPane().add(chartPanel);
        graphWindows.pack();
        graphWindows.setVisible(true);

        return chart;
    }

}
