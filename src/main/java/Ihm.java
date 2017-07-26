import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.*;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Zeke on 19/07/2017.
 */
public class Ihm extends Application
{
    public boolean isComboSprintAlreadyCreated = false;
    public ComboBox comboSprintGraphe;

    public ArrayList<Task> dataListTask;
    public String dateSprintBeg;
    public String dateSprintEnd;

    JFreeChart TaskGraph;
    JFreeChart BusinessGraph;

    /**
     * \fn void start(Stage primaryStage) throws Exception
     * \brief Start the ihm
     * \details Set all item for the primaryStage, button, combobox, and do on...
     *
     * \param primaryStage the main stage of the Javafx Ihm
     */
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Interface Evolution");

        ObservableList<String> optionsProject = getListProjectName();


        //primaryStage.getIcons().add(new Image("t.png"));
        primaryStage.setResizable(false);
        primaryStage.setHeight(500);
        primaryStage.setWidth(900);
        primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new MyEvent());

        BorderPane pane = new BorderPane();
        GridPane paneGraph = new GridPane();
        GridPane paneCombo = new GridPane();
        GridPane paneOption = new GridPane();


        ComboBox comboBoxProject = new ComboBox(optionsProject);
        comboBoxProject.setValue("Project");
        comboBoxProject.setOnAction(event -> {
            Project p = getProjectByTitle(comboBoxProject.getValue().toString());
            if (isComboSprintAlreadyCreated == false)
            {
                ComboBox comboBoxSprint = new ComboBox(getListSprintName(p));
                comboBoxSprint.setValue("Sprint");
                paneCombo.add(comboBoxSprint, 0, 2);

                comboSprintGraphe = comboBoxSprint;
                isComboSprintAlreadyCreated = true;
            }
            else
            {
                deleteGridNodeByColRow(paneCombo, 0, 2);
                ComboBox comboBoxSprint = new ComboBox(getListSprintName(p));
                comboBoxSprint.setValue("Sprint");

                comboSprintGraphe = comboBoxSprint;
                paneCombo.add(comboBoxSprint, 0, 2);
            }
        });


        Button buttonTask = new Button("Graphe des Tâches");
        buttonTask.setOnAction(event -> {
            try {
                Project p = getProjectByTitle(comboBoxProject.getValue().toString());
                String sprintName = comboSprintGraphe.getValue().toString();
                Sprint s = getSprintByTitle(sprintName, p);

                DataGraph data = createDataGraphe(s);
                TaskGraph = GrapheChart.launchTask(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        Button buttonBusiness = new Button("Graphe Business");
        buttonBusiness.setOnAction(event -> {
            try {
                Project p = getProjectByTitle(comboBoxProject.getValue().toString());
                String sprintName = comboSprintGraphe.getValue().toString();
                Sprint s = getSprintByTitle(sprintName, p);

                DataGraph data = createDataGraphe(s);
                BusinessGraph = GrapheChart.launchBusiness(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        Button buttonSave = new Button("Sauvegarder les graphes");
        buttonSave.setOnAction(event -> {
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateFormat.format(date).replace("/", "_");

            String projectString = comboBoxProject.getValue().toString().replace(" ", "_");
            String sprintString = comboSprintGraphe.getValue().toString();

            String path = System.getProperty("user.dir");
            String finalPathTask = path + "/src/main/java/graphImage/" + projectString + "_" + sprintString + "_taskgraph_" + dateString + ".jpeg";
            String finalPathBusiness = path + "/src/main/java/graphImage/" + projectString + "_" + sprintString + "_businessgraph_" + dateString + ".jpeg";

            try {
                if (TaskGraph != null && BusinessGraph != null) {
                    File fileTask = new File(finalPathTask);
                    File fileBusiness = new File(finalPathBusiness);
                    ChartUtilities.saveChartAsPNG(fileTask, TaskGraph, 500, 300);
                    ChartUtilities.saveChartAsPNG(fileBusiness, BusinessGraph, 500, 300);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        Button buttonClose = new Button("Quitter");
        buttonClose.setOnAction(event -> {
            primaryStage.close();
        });

        //Set items to panel
        paneCombo.add(comboBoxProject, 0, 0);
        paneCombo.setHgap(0.5f);

        paneGraph.add(buttonTask, 0, 0, 2, 1);
        paneGraph.add(buttonBusiness, 0, 1, 1, 1);
        paneGraph.setHgap(0.5f);

        paneOption.add(buttonSave, 0, 0);
        paneOption.add(buttonClose, 9, 0);

        pane.setLeft(paneCombo);
        pane.setRight(paneGraph);
        pane.setTop(paneOption);
        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * \fn ObservableList<String> getListProjectName()
     * \brief Get the list of all the project name
     * \details Call the api to get all project name
     *
     * \return an observablelist of string
     */
    public ObservableList<String> getListProjectName()
    {
        CallerApi call = new CallerApi();
        ObservableList<String> optionsProject = FXCollections.observableArrayList();

        String jsonStat = null;
        try {
            jsonStat = call.sendGet("http://127.0.0.1:3000/scrummary/stats");
            java.util.List<Stat> listStat = call.getListStatFromJson(jsonStat);

            for (Stat s : listStat)
            {
                String jsonProject = call.sendGet("http://127.0.0.1:3000/scrummary/projects/" + s.getId_project());
                Project p = call.getProjectFromJson(jsonProject);
                optionsProject.add(p.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optionsProject;
    }

    /**
     * \fn ObservableList<String> getListSprintName(Project project)
     * \brief Get the list of all the sprint name
     * \details Call the api to get all sprint name
     *
     * \param project the current project
     * \return an observablelist of string
     */
    public ObservableList<String> getListSprintName(Project project)
    {
        CallerApi call = new CallerApi();
        ObservableList<String> optionsSprint = FXCollections.observableArrayList();
        try {
            for (int idSprint : project.getId_sprint())
            {
                String json = call.sendGet("http://127.0.0.1:3000/scrummary/sprints/" + idSprint);
                Sprint s = call.getSprintFromJson(json);
                optionsSprint.add(s.getTitle());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return optionsSprint;
    }

    /**
     * \fn Project getProjectByTitle(String title)
     * \brief Get the projet by his title
     * \details Call the api to get the good project by his title
     *
     * \param title the project title
     * \return an project object
     */
    public Project getProjectByTitle(String title)
    {
        CallerApi call = new CallerApi();
        try {
            String json = call.sendGet("http://127.0.0.1:3000/scrummary/stats");
            java.util.List<Stat> listStat = call.getListStatFromJson(json);

            for (Stat s : listStat)
            {
                String jsonProject = call.sendGet("http://127.0.0.1:3000/scrummary/projects/" + s.getId_project());
                Project p = call.getProjectFromJson(jsonProject);
                if (p.getTitle().equals(title))
                    return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * \fn Sprint getSprintByTitle(String title, Project project)
     * \brief Get the sprint by his title
     * \details Call the api to get the good sprint by his title
     *
     * \param title the sprint title
     * \param project the current project
     * \return an sprint object
     */
    public Sprint getSprintByTitle(String title, Project project)
    {
        CallerApi call = new CallerApi();
        try {
            for (int idSprint : project.getId_sprint())
            {
                String json = call.sendGet("http://127.0.0.1:3000/scrummary/sprints/" + idSprint);
                Sprint s = call.getSprintFromJson(json);
                if (s.getTitle().equals(title))
                    return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * \fn void deleteGridNodeByColRow(GridPane pane, int column, int row)
     * \brief Delete item in a gridpane with the column and the row
     * \details parcours each item in the gridpane and delete the good one
     *
     * \param pane the gridpane
     * \param int the index of the column
     * \param int the index of the row
     */
    public void deleteGridNodeByColRow(GridPane pane, int column, int row) {
        ObservableList<Node> childrens = pane.getChildren();
        for(Node node : childrens) {
            if(node instanceof ComboBox && pane.getRowIndex(node) == row && pane.getColumnIndex(node) == column) {
                pane.getChildren().remove(node);
                break;
            }
        }
    }

    /**
     * \fn DataGraph createDataGraphe(Sprint sprint) throws ParseException
     * \brief Construct an object DataGraph for graph
     * \details Build an object Datagraph which contain all data for graph
     *
     * \param sprint the current sprint
     * \return an datagraph object
     */
    public DataGraph createDataGraphe(Sprint sprint) throws ParseException {
        CallerApi call = new CallerApi();
        ArrayList<Task> listTask = new ArrayList<>();
        try {
            // Build array list of task
            for (int idTask : sprint.getId_listTasks())
            {
                String jsonTask = call.sendGet("http://127.0.0.1:3000/scrummary/tasks/" + idTask);
                Task t = call.getTaskFromJson(jsonTask);
                listTask.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long duration = AlgorithmGraph.getDurationSprint(sprint.getBeginningDate(), sprint.getEndDate());
        DataGraph result = new DataGraph(listTask, sprint.getBeginningDate(), sprint.getEndDate(), duration);
        setListDateForDataGraphe(result);

        return result;
    }

    /**
     * \fn void setListDateForDataGraphe(DataGraph data) throws ParseException
     * \brief Construct the list of date in string type
     * \details from the date begin and date end of the sprint, this method build a list of string
     *  which contain all the date in string
     *
     * \param data the datagraph object
     */
    public void setListDateForDataGraphe(DataGraph data) throws ParseException {

        String formatBeginDate = data.getDateBeg().substring(0, 10);
        String formatEndDate = data.getDateEnd().substring(0, 10);

        formatBeginDate = formatBeginDate.replaceAll("-", "/");
        formatEndDate = formatEndDate.replaceAll("-", "/");

        ArrayList<Date> arrayDate = AlgorithmGraph.getListDate(formatBeginDate, formatEndDate);

        ArrayList<String> arrayDateString = new ArrayList<String>();

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        for (Date d : arrayDate)
        {
            arrayDateString.add(format.format(d));
        }
        data.setListDateName(arrayDateString.toArray(new String[0]));
    }

    public static void main(String[] args) {
        launch(args);
    }

    class MyEvent implements EventHandler<WindowEvent> {
        public void handle(WindowEvent event) {
            System.out.println("Bye Bye Evolution");
        }
    }
}
