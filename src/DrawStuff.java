import javafx.application.Application;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.awt.geom.GeneralPath;

public class DrawStuff extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Draw triangle");
        GeneralPath polygon = new GeneralPath();
        //under construction...
    }
}
