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
import java.util.ArrayList;

/**
 * Created by Zeke on 14/07/2017.
 */
public class GrapheChart extends ApplicationFrame
{

    public GrapheChart(String title)
    {
        super(title);
    }

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

    public JFreeChart createTestChart()
    {
        JFreeChart chart = ChartFactory.createXYAreaChart(
                "Graphe des Tâches",
                "Jours",
                "Heure Total",
                createDataset(),
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot xyPlot = (XYPlot) chart.getPlot();


        ValueAxis xAxis = xyPlot.getDomainAxis();
        ValueAxis yAxis = xyPlot.getRangeAxis();
        XYAnnotation diagonalLine = new XYLineAnnotation(xAxis.getRange().getUpperBound(), yAxis.getRange().getLowerBound()
            , xAxis.getRange().getLowerBound(), yAxis.getRange().getUpperBound());
        xyPlot.addAnnotation(diagonalLine);


        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        xyPlot.setRenderer(renderer);


        return chart;
    }

    public XYDataset createDataset()
    {
        XYSeries taskLine = new XYSeries("models.Task Todo");
        taskLine.add(1, 30);
        taskLine.add(2, 22);
        taskLine.add(3, 18);
        taskLine.add(4, 10);
        taskLine.add(5, 5);

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(taskLine);



        return dataset;

    }



    public static void launchTask(DataGraph d)
    {
        JDialog graphWindows = new JDialog();
        graphWindows.setTitle("Graphique des tâches");


        final double[][] data = new double[][] {
                AlgorithmGraphe.createOptimalData(30),
                {30, 30, 26, 26, 18, 14}

        };


        String[] rowGraphe = {"Progression Optimale", "Graphe des tâches"};
        String[] columnsGraphe = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};

        GrapheChart graphe = new GrapheChart("GRAPHE Test");
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
    }

    public static void launchBusiness(DataGraph d)
    {
        //TO FIX CONTINUE: Datagraph good with string[] date
        //to do AlgorithmGraph.createadataset

        JDialog graphWindows = new JDialog();
        graphWindows.setTitle("Graphique Business");

        final double[][] dataB = new double[][] {
                {0, 5, 15, 25, 30, 50}

        };

        String[] columnsGraphe = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
        String[] rowGrapheB = {"Graphe Business"};

        GrapheChart graphe = new GrapheChart("GRAPHE Test");
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
    }

}
