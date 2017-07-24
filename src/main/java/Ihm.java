import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.*;

import java.io.IOException;
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

    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Interface Evolution");

        ObservableList<String> optionsProject = getListProjectName();


        //TO FIX: add icone
        //primaryStage.getIcons().add(new Image("t.png"));
        primaryStage.setResizable(false);
        primaryStage.setHeight(500);
        primaryStage.setWidth(900);
        primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new MyEvent());




       /* Button buttonOk = new Button("OK");
        //Ajout de la couleur
        buttonOk.setStyle("-fx-color: #fb0");
        buttonOk.setOnAction(event -> {
            System.out.println("Hey Yo");
        });*/

        BorderPane pane = new BorderPane();
        GridPane gridPane = new GridPane();
        GridPane paneCombo = new GridPane();


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
                GrapheChart.launchTask(data);
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
                GrapheChart.launchBusiness(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });


        //Set items to panel
        paneCombo.add(comboBoxProject, 0, 0);
        paneCombo.setHgap(0.5f);

        gridPane.add(buttonTask, 0, 0, 2, 1);
        gridPane.add(buttonBusiness, 0, 1, 1, 1);
        gridPane.setHgap(0.5f);

        pane.setLeft(paneCombo);
        pane.setRight(gridPane);
        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

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

    public void deleteGridNodeByColRow(GridPane pane, int column, int row)
    {
        ObservableList<Node> childrens = pane.getChildren();
        for(Node node : childrens) {
            if(node instanceof ComboBox && pane.getRowIndex(node) == row && pane.getColumnIndex(node) == column) {
                pane.getChildren().remove(node);
                break;
            }
        }
    }

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
        long duration = AlgorithmGraphe.getDurationSprint(sprint.getBeginningDate(), sprint.getEndDate());
        DataGraph result = new DataGraph(listTask, sprint.getBeginningDate(), sprint.getEndDate(), duration);
        setListDateForDataGraphe(result);

        return result;
    }

    public void setListDateForDataGraphe(DataGraph data) throws ParseException {

        String formatBeginDate = data.getDateBeg().substring(0, 10);
        String formatEndDate = data.getDateEnd().substring(0, 10);

        formatBeginDate = formatBeginDate.replaceAll("-", "/");
        formatEndDate = formatEndDate.replaceAll("-", "/");

        ArrayList<Date> arrayDate = AlgorithmGraphe.getListDate(formatBeginDate, formatEndDate);

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
            System.out.println("Exit Evolution");
        }
    }
}
