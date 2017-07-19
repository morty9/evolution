import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by Zeke on 19/07/2017.
 */
public class Ihm extends Application
{

    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Interface Evolution");


        //add icone
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

        Button buttonTask = new Button("Graphe des Tâches");
        buttonTask.setOnAction(event -> {
            GrapheChart.launch("ButtonTask");
        });
        Button buttonBusiness = new Button("Graphe Business");
        buttonBusiness.setOnAction(event -> {
            GrapheChart.launch("ButtonBusiness");
        });

        gridPane.add(buttonTask, 0, 0, 2, 1);
        gridPane.add(buttonBusiness, 0, 1, 1, 1);
        gridPane.setHgap(0.5f);
        pane.setRight(gridPane);
        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.show();
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
