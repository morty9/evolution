import org.jfree.chart.ChartUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Zeke on 10/07/2017.
 */
public class InterfaceGraphe extends JFrame
{

    JPanel MainPanel = new JPanel();
    JPanel ButtonPanel = new JPanel();

    public InterfaceGraphe()
    {

        this.setTitle("Scrummary Graphique");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.SetPanel();
    }

    public void SetPanel()
    {
        String[] listProject = new String[] {"Project1", "Project2", "Project3", "Project4"};
        //MainPanel.setBackground(Color.BLUE);

        JButton buttonBusiness = new JButton("Graphe du Business");
        JButton buttonTask = new JButton("Graphe des tâches");
        //JComboBox<String> comboProject = new JComboBox<String>(listProject);




        MainPanel.add(buttonBusiness, BorderLayout.NORTH);
        MainPanel.add(buttonTask, BorderLayout.NORTH);


        this.setContentPane(MainPanel);
    }

    //TO FIX Save graphe
    //ChartUtilities.saveChartasJPEG(new File("C:\\chart.jpg"), chart, 500, 300);

    public void launch()
    {
        this.setVisible(true);
    }
}
